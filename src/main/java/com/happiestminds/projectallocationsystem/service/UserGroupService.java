package com.happiestminds.projectallocationsystem.service;

import java.util.List;

import com.happiestminds.projectallocationsystem.entity.UserGroupEntity;


/**
 * @author rasool.shaik
 *
 */

public interface UserGroupService {

	public void addUserGroup(UserGroupEntity userGroup);
	
	public void updateUserGroup(UserGroupEntity userGroup);
	
	public void deleteUserGroup(UserGroupEntity userGroup);
	
	public UserGroupEntity getUserGroup(String groupId);
	
	public List<UserGroupEntity> getAllUserGroups();
}
