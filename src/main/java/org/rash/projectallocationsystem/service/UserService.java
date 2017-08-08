package org.rash.projectallocationsystem.service;

import org.rash.projectallocationsystem.Dto.UserDto;
import org.rash.projectallocationsystem.Dto.batch.UserGroupListDto;
import org.rash.projectallocationsystem.Dto.batch.UserListDto;
import org.springframework.transaction.annotation.Transactional;

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
