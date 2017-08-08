package org.rash.projectallocationsystem.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.rash.projectallocationsystem.Dto.ModuleDto;
import org.rash.projectallocationsystem.Dto.OperationDto;
import org.rash.projectallocationsystem.Dto.UserGroupDto;
import org.rash.projectallocationsystem.Dto.batch.UserGroupListDto;
import org.rash.projectallocationsystem.response.UserGroupOptionsResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rasool.shaik
 * 
 */

public interface UserGroupService {

	@Transactional
	void addUserGroup(UserGroupDto userGroup);

	@Transactional
	void updateUserGroup(UserGroupDto userGroup);

	@Transactional
	void deleteUserGroup(UserGroupDto userGroup);

	@Transactional(readOnly = true)
	UserGroupListDto getAllUserGroups(int startIndex, int pageSize, String sorting);

	@Transactional(readOnly = true)
	Map<String, List<OperationDto>> fetchOperationsByGroup(String groupId);

	@Transactional
	boolean applyRightsToGroup(String groupId, List<String> operations);

	@Transactional(readOnly = true)
	public Set<ModuleDto> fetchModulesByRights(Collection<GrantedAuthority> authorities);

	@Transactional(readOnly = true)
	UserGroupOptionsResponse getAllUserGroupsOptions();

	@Transactional(readOnly = true)
	int getCountAllUserGroups();

}
