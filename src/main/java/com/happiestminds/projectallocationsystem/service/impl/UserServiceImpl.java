package com.happiestminds.projectallocationsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happiestminds.projectallocationsystem.dao.UserDAO;
import com.happiestminds.projectallocationsystem.entity.UserEntity;
import com.happiestminds.projectallocationsystem.service.UserService;

/**
 * @author rasool.shaik
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;

	/**
	 * 
	 */
	public UserServiceImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.happiestminds.projectallocationsystem.service.UserService#add(com
	 * .happiestminds.projectallocationsystem.entity.UserEntity)
	 */
	@Override
	public boolean add(UserEntity userEntity) {
		UserEntity user = userDao.saveOrUpdate(userEntity);
		if (user != null)
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.happiestminds.projectallocationsystem.service.UserService#update(
	 * com.happiestminds.projectallocationsystem.entity.UserEntity)
	 */
	@Override
	public UserEntity update(UserEntity user) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.happiestminds.projectallocationsystem.service.UserService#delete(
	 * com.happiestminds.projectallocationsystem.entity.UserEntity)
	 */
	@Override
	public boolean delete(UserEntity user) {
		return false;
	}

}
