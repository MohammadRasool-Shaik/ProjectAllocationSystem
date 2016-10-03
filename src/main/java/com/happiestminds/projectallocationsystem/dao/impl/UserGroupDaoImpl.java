package com.happiestminds.projectallocationsystem.dao.impl;

import org.springframework.stereotype.Repository;

import com.happiestminds.projectallocationsystem.dao.UserGroupDAO;
import com.happiestminds.projectallocationsystem.entity.UserGroupEntity;

/**
 * @author rasool.shaik
 * 
 */
@Repository
public class UserGroupDaoImpl extends AbstractDaoImpl<UserGroupEntity, String> implements UserGroupDAO {

	protected UserGroupDaoImpl() {
		super(UserGroupEntity.class);
	}

}
