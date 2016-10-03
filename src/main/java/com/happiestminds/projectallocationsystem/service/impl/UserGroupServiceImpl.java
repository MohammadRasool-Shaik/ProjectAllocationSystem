package com.happiestminds.projectallocationsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.dao.UserGroupDAO;
import com.happiestminds.projectallocationsystem.entity.UserGroupEntity;
import com.happiestminds.projectallocationsystem.service.UserGroupService;

/**
 * @author rasool.shaik
 * 
 */
@Service
public class UserGroupServiceImpl implements UserGroupService {

	@Autowired
	private UserGroupDAO userGroupDAO;

	/**
	 * 
	 */
	public UserGroupServiceImpl() {
	}

	@Transactional
	@Override
	public void addUserGroup(UserGroupEntity userGroup) {

		userGroupDAO.saveOrUpdate(userGroup);
	}

	@Override
	@Transactional
	public void updateUserGroup(UserGroupEntity userGroup) {
		userGroupDAO.saveOrUpdate(userGroup);

	}

	@Override
	@Transactional
	public void deleteUserGroup(UserGroupEntity userGroup) {
		userGroupDAO.delete(userGroup);

	}

	@Override
	@Transactional
	public UserGroupEntity getUserGroup(String groupId) {
		UserGroupEntity userGroupEntity = userGroupDAO.findById(groupId);
		return userGroupEntity;
	}

	@Override
	@Transactional
	public List<UserGroupEntity> getAllUserGroups() {
		List<UserGroupEntity> userGroups = userGroupDAO.findAll("UserGroupEntity");
		return userGroups;
	}

}
