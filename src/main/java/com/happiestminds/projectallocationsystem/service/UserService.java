package com.happiestminds.projectallocationsystem.service;

import com.happiestminds.projectallocationsystem.entity.UserEntity;

/**
 * @author rasool.shaik
 *
 */
public interface UserService {
	boolean add(UserEntity user);
	UserEntity update(UserEntity user);
	boolean delete(UserEntity user);
	
}
