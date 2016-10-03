package com.happiestminds.projectallocationsystem.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.entity.UserEntity;

/**
 * @author rasool.shaik
 * 
 */
public interface UserService {
	@Transactional
	public boolean addUser(UserEntity user);

	@Transactional
	public void updateUser(UserEntity user, String groupId);

	@Transactional
	public void deleteUser(UserEntity user);

	@Transactional
	public List<UserEntity> getAllUsersWithGroups();

}
