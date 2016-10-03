package com.happiestminds.projectallocationsystem.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.entity.ModuleEntity;
import com.happiestminds.projectallocationsystem.entity.OperationEntity;
import com.happiestminds.projectallocationsystem.entity.UserEntity;
import com.happiestminds.projectallocationsystem.entity.UserGroupEntity;

/**
 * @author rasool.shaik
 * 
 */

public interface UserGroupService {

	@Transactional
	boolean addUserGroup(UserGroupEntity userGroup);

	@Transactional
	void updateUserGroup(UserGroupEntity userGroup);

	@Transactional
	void deleteUserGroup(UserGroupEntity userGroup);

	@Transactional
	List<UserGroupEntity> getAllUserGroups();

	@Transactional
	Map<String, List<OperationEntity>> fetchOperationsByGroup(String groupId);

	@Transactional
	boolean applyRightsToGroup(String groupId, String operations);

	@Transactional
	boolean revokeGroupRightsFromGroup(String groupId, String operations);

	@Transactional
	Set<ModuleEntity> fetchModulesByRights(Collection<GrantedAuthority> authorities);

	@Transactional
	List<UserEntity> fetchAllUsersWithGroups(Set<String> groupIds);

}
