package com.happiestminds.projectallocationsystem.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.happiestminds.projectallocationsystem.Dto.ModuleDto;
import com.happiestminds.projectallocationsystem.Dto.OperationDto;
import com.happiestminds.projectallocationsystem.Dto.StatusDto;
import com.happiestminds.projectallocationsystem.Dto.UserGroupDto;
import com.happiestminds.projectallocationsystem.Dto.UserGroupOptionDto;
import com.happiestminds.projectallocationsystem.Dto.batch.UserGroupListDto;
import com.happiestminds.projectallocationsystem.dao.GroupRightsDAO;
import com.happiestminds.projectallocationsystem.dao.OperationDAO;
import com.happiestminds.projectallocationsystem.dao.UserGroupDAO;
import com.happiestminds.projectallocationsystem.entity.GroupRightsEntity;
import com.happiestminds.projectallocationsystem.entity.GroupRightsPK;
import com.happiestminds.projectallocationsystem.entity.ModuleEntity;
import com.happiestminds.projectallocationsystem.entity.OperationEntity;
import com.happiestminds.projectallocationsystem.entity.UserGroupEntity;
import com.happiestminds.projectallocationsystem.enumerator.StatusCodeEnum;
import com.happiestminds.projectallocationsystem.exception.CustomGenericException;
import com.happiestminds.projectallocationsystem.response.UserGroupOptionsResponse;
import com.happiestminds.projectallocationsystem.service.UserGroupService;

/**
 * @author rasool.shaik
 * 
 */
@Service("userGroupService")
public class UserGroupServiceImpl implements UserGroupService {

	private static Logger logger = LoggerFactory.getLogger(UserGroupServiceImpl.class.getSimpleName());

	@Autowired
	private UserGroupDAO userGroupDAO;

	@Autowired
	private GroupRightsDAO groupRightsDAO;

	@Autowired
	private OperationDAO operationDAO;

	/**
	 * 
	 */
	public UserGroupServiceImpl() {
	}

	@Override
	public void addUserGroup(UserGroupDto userGroup) {
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);

		UserGroupEntity userGroupEntity = userGroupDAO.findById(userGroup.getGroupId());
		if (userGroupEntity == null) {
			userGroup.setGroupId(userGroup.getGroupId().toUpperCase());
			userGroupEntity = new UserGroupEntity();
			BeanUtils.copyProperties(userGroup, userGroupEntity);
			userGroupDAO.save(userGroupEntity);
			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("UserGroup Successfully Added");
		} else {
			statusDto.setStatusMessage("UserGroup with " + userGroup.getGroupId() + " already exist, Try another?");
		}
		userGroup.setStatusDto(statusDto);
	}

	@Override
	public void updateUserGroup(UserGroupDto userGroup) {
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		UserGroupEntity userGroupEntity = new UserGroupEntity();
		BeanUtils.copyProperties(userGroup, userGroupEntity);
		userGroupEntity = userGroupDAO.update(userGroupEntity);
		if (userGroupEntity != null) {
			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("UserGroup Successfully Updated");
		} else {
			statusDto.setStatusMessage("UserGroup doesn't updated successfully");
		}
		userGroup.setStatusDto(statusDto);
	}

	@Override
	public void deleteUserGroup(UserGroupDto userGroup) {
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		String groupId = userGroup.getGroupId();
		groupRightsDAO.revokeGroupRightsFromGroup(groupId, null);
		int noUserGroupEntitiesDeleted = userGroupDAO.deleteById("UserGroupEntity", groupId);
		UserGroupDto userGroupTemp = new UserGroupDto();
		BeanUtils.copyProperties(userGroupTemp, userGroup);
		if (noUserGroupEntitiesDeleted > 0) {
			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("UserGroup Successfully Deleted");
		} else {
			statusDto.setStatusMessage("UserGroup not Deleted");
		}
		userGroup.setStatusDto(statusDto);
	}

	@Override
	public UserGroupListDto getAllUserGroups(int startIndex, int pageSize, String sortVar) {
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		UserGroupListDto userGroupList = new UserGroupListDto();
		List<UserGroupEntity> userGroups = (List<UserGroupEntity>) userGroupDAO.fetchAllUsersGroups(startIndex, pageSize, sortVar);
		for (UserGroupEntity userGroupEntity : userGroups) {
			UserGroupDto userGroup = new UserGroupDto();
			BeanUtils.copyProperties(userGroupEntity, userGroup);
			userGroupList.getUserGroups().add(userGroup);
		}
		statusDto.setStatusCode(StatusCodeEnum.OK);
		userGroupList.setStatusDto(statusDto);
		return userGroupList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happiestminds.projectallocationsystem.service.UserGroupService#
	 * assignGroupRights(java.lang.String)
	 */
	@Override
	public boolean applyRightsToGroup(String groupId, List<String> operationList) {
		boolean isAssigned = false;
		try {

			if (operationList == null || operationList.size() == 0) {
				groupRightsDAO.revokeGroupRightsFromGroup(groupId, null);
				return true;
			}
			List<String> operationsByGroup = groupRightsDAO.fetchGroupRightsByGroup(groupId);

			GroupRightsEntity groupRightsEntity = new GroupRightsEntity();
			GroupRightsPK groupRightPK = new GroupRightsPK();

			for (String operationId : operationList) {
				if (!operationsByGroup.contains(operationId)) {
					groupRightPK.setGroupId(groupId);
					groupRightPK.setOperationId(operationId);
					groupRightsEntity.setGroupRightPk(groupRightPK);
					groupRightsDAO.save(groupRightsEntity);
				}
			}

			for (String operationId : operationsByGroup) {
				if (!operationList.contains(operationId)) {
					groupRightsDAO.revokeGroupRightsFromGroup(groupId, operationId);
				}
			}

			isAssigned = true;
		} catch (HibernateException hex) {
			logger.error("HibernateException Exception OCCURED WHILE FETCHING DATO FROM/USING GROUPRIGHTSENTITY" + hex);
			throw new CustomGenericException(StatusCodeEnum.FAILURE, hex.getMessage());

		} catch (Exception ex) {
			logger.error("Exception OCCURED WHILE PROCESSING DATA" + ex);
			throw new CustomGenericException(StatusCodeEnum.FAILURE, ex.getMessage());
		}
		return isAssigned;
	}

	@Override
	public Map<String, List<OperationDto>> fetchOperationsByGroup(String groupId) {
		Map<String, List<OperationDto>> operationsByModule = new HashMap<String, List<OperationDto>>();
		try {
			Set<String> groupRights = fetchGroupRightsByGroup(groupId);
			operationsByModule(operationsByModule, groupRights);
		} catch (HibernateException hex) {
			logger.error("HibernateException Exception OCCURED WHILE FETCHING DATO FROM OPERATION TABLES" + hex);
			throw new CustomGenericException(StatusCodeEnum.FAILURE, hex.getMessage());
		} catch (Exception ex) {
			logger.error("Exception OCCURED WHILE PROCESSING DATA" + ex);
			throw new CustomGenericException(StatusCodeEnum.FAILURE, ex.getMessage());
		}
		return operationsByModule;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happiestminds.projectallocationsystem.service.OperationService#
	 * fetchOperationsByUserGroup(java.lang.String)
	 */

	private Set<String> fetchGroupRightsByGroup(String groupId) {
		Set<String> operations = new HashSet<String>();
		List<GroupRightsEntity> operationsByGroup = userGroupDAO.fetchGroupRightsByGroup(groupId);
		for (GroupRightsEntity operationByGroup : operationsByGroup) {
			operations.add(operationByGroup.getGroupRightPk().getOperationId());
		}
		return operations;
	}

	/**
	 * @param operationsByModule
	 * @param groupRights
	 */

	// TODO : two separate calls to get users and to get usergroups, instead
	// we can use right outer join 2 get both information in one db call,but
	// right outer join not possible in Hibernate
	// That's y now i am maintaining Bi-direcition relationship b/w usergroup
	// and user so we easily get from db
	private void operationsByModule(Map<String, List<OperationDto>> operationsByModule, Set<String> groupRights) {
		List<OperationEntity> operations = operationDAO.fetchOperationsWithModule();
		for (OperationEntity tempOperation : operations) {
			List<OperationDto> operataionsList = new ArrayList<OperationDto>();
			for (OperationEntity operation : operations) {
				OperationDto operationDto = new OperationDto();
				BeanUtils.copyProperties(operation, operationDto);
				// TODO : check Module info really necessary to display info in
				// operations
				// ModuleDto moduleDto=new ModuleDto();
				// BeanUtils.copyProperties(operation.getModule(),moduleDto);
				if (tempOperation.getModule().getModuleId().equals(operation.getModule().getModuleId())) {
					if (groupRights.contains(operation.getOperationId())) {
						operationDto.setIsChecked("selected");
					}
					operataionsList.add(operationDto);
				}
			}
			operationsByModule.put(tempOperation.getModule().getModuleName(), operataionsList);
		}
	}

	public Map<Integer, ModuleDto> fetchModulesByRights1(Collection<GrantedAuthority> authorities) {
		Set<String> operationFromAuthorities = fetchOperationsFromAuthorities(authorities);
		List<OperationEntity> operations = operationDAO.fetchOperationsWithModule();
		Map<Integer, ModuleDto> modules = new HashMap<Integer, ModuleDto>();
		for (OperationEntity operation : operations) {
			if (operationFromAuthorities.contains(operation.getOperationId())) {
				ModuleEntity module = operation.getModule();
				ModuleDto moduleDto = new ModuleDto();
				BeanUtils.copyProperties(module, moduleDto);
				modules.put(operation.getModule().getViewOrder(), moduleDto);
			}
		}

		return modules;
	}

	@Override
	public Set<ModuleDto> fetchModulesByRights(Collection<GrantedAuthority> authorities) {
		Set<String> operationFromAuthorities = fetchOperationsFromAuthorities(authorities);
		List<OperationEntity> operations = operationDAO.fetchOperationsWithModule();
		Set<ModuleDto> modules = new TreeSet<ModuleDto>();

		for (OperationEntity operation : operations) {
			if (operationFromAuthorities.contains(operation.getOperationId())) {
				ModuleEntity module = operation.getModule();
				ModuleDto moduleDto = new ModuleDto();
				BeanUtils.copyProperties(module, moduleDto);
				if (!modules.contains(moduleDto)) {
					modules.add(moduleDto);
				}
			}
		}
		return modules;
	}

	public Set<String> fetchOperationsFromAuthorities(Collection<GrantedAuthority> authorities) {
		Set<String> operations = new HashSet<String>();
		if (authorities != null) {
			for (GrantedAuthority grantedAuthority : authorities) {
				operations.add(grantedAuthority.getAuthority());
			}
		}
		return operations;
	}

	@Override
	public UserGroupOptionsResponse getAllUserGroupsOptions() {
		UserGroupOptionsResponse uGroupOptionsResponse = new UserGroupOptionsResponse();
		Collection<UserGroupEntity> userGroupList = userGroupDAO.findAll();
		uGroupOptionsResponse.setResult("ERROR");
		if (!userGroupList.isEmpty()) {
			for (UserGroupEntity userGroupEntiy : userGroupList) {
				UserGroupOptionDto userGroupOptionDto = new UserGroupOptionDto();
				userGroupOptionDto.setDisplayText(userGroupEntiy.getGroupName());
				userGroupOptionDto.setValue(userGroupEntiy.getGroupId());
				uGroupOptionsResponse.getOptions().add(userGroupOptionDto);
			}
			uGroupOptionsResponse.setResult("OK");
		} else {
			uGroupOptionsResponse.setMessage("Groups are not available, Contact Administrator");
		}
		return uGroupOptionsResponse;
	}

	@Override
	public int getCountAllUserGroups() {
		return userGroupDAO.getCountAllUserGroups();
	}

}
