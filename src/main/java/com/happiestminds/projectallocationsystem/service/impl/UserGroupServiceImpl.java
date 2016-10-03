package com.happiestminds.projectallocationsystem.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.happiestminds.projectallocationsystem.Dto.StatusDto;
import com.happiestminds.projectallocationsystem.dao.GroupRightsDAO;
import com.happiestminds.projectallocationsystem.dao.OperationDAO;
import com.happiestminds.projectallocationsystem.dao.UserGroupDAO;
import com.happiestminds.projectallocationsystem.entity.GroupRightsEntity;
import com.happiestminds.projectallocationsystem.entity.GroupRightsPK;
import com.happiestminds.projectallocationsystem.entity.ModuleEntity;
import com.happiestminds.projectallocationsystem.entity.OperationEntity;
import com.happiestminds.projectallocationsystem.entity.UserEntity;
import com.happiestminds.projectallocationsystem.entity.UserGroupEntity;
import com.happiestminds.projectallocationsystem.enumerator.StatusCodeEnum;
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

	private static final StatusDto statusDto = new StatusDto();

	/**
	 * 
	 */
	public UserGroupServiceImpl() {
	}

	@Override
	public boolean addUserGroup(UserGroupEntity userGroup) {
		boolean isUserGroupAdded = false;
		try {
			UserGroupEntity userGroupEntity = userGroupDAO.findById(userGroup.getGroupId());
			if (userGroupEntity == null) {
				userGroup.setGroupId(userGroup.getGroupId().toUpperCase());
				userGroupDAO.save(userGroup);
				isUserGroupAdded = true;
			}
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED WHILE ADDING USERGROUP IN ENTITY", hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED WHILE ADDING USERGROUP IN ENTITY", ex);
		}
		return isUserGroupAdded;
	}

	@Override
	public void updateUserGroup(UserGroupEntity userGroup) {
		try {
			userGroupDAO.saveOrUpdate(userGroup);
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED WHILE UPDATING USERGROUP IN ENTITY", hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED WHILE UPDATING USERGROUP IN ENTITY", ex);
		}

	}

	@Override
	public void deleteUserGroup(UserGroupEntity userGroup) {
		try {
			userGroupDAO.delete(userGroup);
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED WHILE DELETING USERGROUP IN ENTITY", hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED WHILE DELETING USERGROUP IN ENTITY", ex);
		}

	}

	@Override
	public List<UserGroupEntity> getAllUserGroups() {
		List<UserGroupEntity> userGroups = null;
		try {
			userGroups = (List<UserGroupEntity>) userGroupDAO.findAll();
			statusDto.setStatusCode(StatusCodeEnum.SUCCESS);
		} catch (HibernateException hex) {
			statusDto.setStatusCode(StatusCodeEnum.FAILURE);
			statusDto.setStatusMsg(hex.getMessage());
			logger.error("HibernateException Exception OCCURED WHILE FETCHING DATO FROM/USING UserGroupEntity" + hex);
		} catch (Exception ex) {
			statusDto.setStatusCode(StatusCodeEnum.FAILURE);
			statusDto.setStatusMsg(ex.getMessage());
			logger.error("Exception OCCURED WHILE FETCHING USERGROUP FROM ENTITY" + ex);
		}

		return userGroups;
	}

	@Override
	public List<UserEntity> fetchAllUsersWithGroups(Set<String> groupIds) {
		List<UserEntity> users = new ArrayList<UserEntity>();
		List<UserGroupEntity> allUsersWithGroups = userGroupDAO.fetchAllUsersWithGroups();
		for (UserGroupEntity userGroup : allUsersWithGroups) {
			groupIds.add(userGroup.getGroupId());
			users.addAll(userGroup.getUsers());
		}
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happiestminds.projectallocationsystem.service.UserGroupService#
	 * assignGroupRights(java.lang.String)
	 */
	@Override
	public boolean applyRightsToGroup(String groupId, String operations) {
		boolean isAssigned = false;
		try {
			List<String> operationList = Arrays.asList(StringUtils.split(operations, "|"));
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
			statusDto.setStatusCode(StatusCodeEnum.FAILURE);
			statusDto.setStatusMsg(hex.getMessage());
			logger.error("HibernateException Exception OCCURED WHILE FETCHING DATO FROM/USING GROUPRIGHTSENTITY" + hex);
		} catch (Exception ex) {
			statusDto.setStatusCode(StatusCodeEnum.FAILURE);
			statusDto.setStatusMsg(ex.getMessage());
			logger.error("Exception OCCURED WHILE PROCESSING DATA" + ex);
		}
		return isAssigned;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happiestminds.projectallocationsystem.service.UserGroupService#
	 * revokeGroupRightsFromGroup(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean revokeGroupRightsFromGroup(String groupId, String operations) {
		boolean isRevoked = false;
		try {
			String[] operationList = StringUtils.split(operations, "|");
			for (String operationId : operationList) {
				groupRightsDAO.revokeGroupRightsFromGroup(groupId, operationId);
			}
			isRevoked = true;
		} catch (HibernateException hex) {
			logger.error("HibernateException Exception OCCURED WHILE DELETING RIGHT FROM GROUPRIGHTSENTITY" + hex);
		} catch (Exception ex) {
			logger.error("Exception OCCURED WHILE PROCESSING DATA" + ex);
		}
		return isRevoked;
	}

	@Override
	public Map<String, List<OperationEntity>> fetchOperationsByGroup(String groupId) {
		Map<String, List<OperationEntity>> operationsByModule = new HashMap<String, List<OperationEntity>>();
		try {
			Set<String> groupRights = fetchGroupRightsByGroup(groupId);
			operationsByModule(operationsByModule, groupRights);
		} catch (HibernateException hex) {
			statusDto.setStatusCode(StatusCodeEnum.FAILURE);
			statusDto.setStatusMsg(hex.getMessage());
			logger.error("HibernateException Exception OCCURED WHILE FETCHING DATO FROM OPERATION TABLES" + hex);
		} catch (Exception ex) {
			statusDto.setStatusCode(StatusCodeEnum.FAILURE);
			statusDto.setStatusMsg(ex.getMessage());
			logger.error("Exception OCCURED WHILE PROCESSING DATA" + ex);
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
	private void operationsByModule(Map<String, List<OperationEntity>> operationsByModule, Set<String> groupRights) {
		List<OperationEntity> operations = operationDAO.fetchOperationsWithModule();
		for (OperationEntity tempOperation : operations) {
			List<OperationEntity> operataionsList = new ArrayList<OperationEntity>();
			for (OperationEntity operation : operations) {
				if (tempOperation.getModule().getModuleId().equals(operation.getModule().getModuleId())) {
					if (groupRights.contains(operation.getOperationId())) {
						operation.setIsChecked("checked");
					}
					operataionsList.add(operation);
				}
			}
			operationsByModule.put(tempOperation.getModule().getModuleName(), operataionsList);
		}
	}

	// TODO : two separate calls to get users and to get usergroups, instead
	// we can use right outer join 2 get both information in one db call,
	// That's y now i am maintaining Bi-direcition relationship b/w usergroup
	// and user so we easily get from db
	@Override
	public Set<ModuleEntity> fetchModulesByRights(Collection<GrantedAuthority> authorities) {
		Set<String> operationFromAuthorities = fetchOperationsFromAuthorities(authorities);
		List<OperationEntity> operations = operationDAO.fetchOperationsWithModule();
		Set<ModuleEntity> modules = new HashSet<ModuleEntity>();
		for (OperationEntity operation : operations) {
			if (operationFromAuthorities.contains(operation.getOperationId())) {
				modules.add(operation.getModule());
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
}
