package com.happiestminds.projectallocationsystem.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.happiestminds.projectallocationsystem.entity.UserEntity;

/**
 * @author rasool.shaik
 * 
 */
public interface UserDAO extends AbstractDAO<UserEntity, String> {
	public UserEntity findByUserName(String userName) throws HibernateException;
	public List<UserEntity> getAllUsers()throws HibernateException;
	public void updateUserbyId(UserEntity user, String groupId)throws HibernateException;
	
	public UserEntity findByUserByEmail(String emailId) throws HibernateException;
	void updateUserPWD(String userName,String password) throws HibernateException;

}
