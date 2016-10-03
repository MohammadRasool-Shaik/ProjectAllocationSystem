package com.happiestminds.projectallocationsystem.service;

import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.Dto.UserDto;
import com.happiestminds.projectallocationsystem.Dto.batch.UserGroupListDto;
import com.happiestminds.projectallocationsystem.Dto.batch.UserListDto;

/**
 * @author rasool.shaik
 * 
 */
public interface UserService {
	@Transactional
	UserDto addUser(UserDto user);

	@Transactional
	void updateUser(UserDto user);

	@Transactional
	void deleteUser(UserDto user, String userName);

	@Transactional(readOnly = true)
	UserListDto getAllUsersWithGroups(UserGroupListDto groups, int startIndex, int pageSize, String sortVar);

	@Transactional(readOnly = true)
	UserDto getUser(String userName);

	@Transactional(readOnly = true)
	int getAllUserCount();

}
