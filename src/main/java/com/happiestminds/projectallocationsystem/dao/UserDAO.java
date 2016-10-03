package com.happiestminds.projectallocationsystem.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.happiestminds.projectallocationsystem.Dto.UserDto;
import com.happiestminds.projectallocationsystem.entity.UserEntity;

/**
 * @author rasool.shaik
 * 
 */
public interface UserDAO extends AbstractDAO<UserEntity, Integer> {
	UserEntity findByUserName(String userName) throws HibernateException;

	List<UserEntity> getAllUsers(int startIndex, int pageSize, String sortVar) throws HibernateException;

	int updateUserbyName(UserDto user) throws HibernateException;

	UserEntity findByUserByEmail(String emailId) throws HibernateException;

	int updateUserPWD(String userName, String password) throws HibernateException;

	List<UserEntity> findByUserNameOREmail(UserDto user) throws HibernateException;

	int deleteUserByName(String userName) throws HibernateException;

	int getAllUsersCount();

}
