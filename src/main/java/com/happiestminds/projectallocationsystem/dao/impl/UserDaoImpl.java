package com.happiestminds.projectallocationsystem.dao.impl;

import org.springframework.stereotype.Repository;

import com.happiestminds.projectallocationsystem.dao.UserDAO;
import com.happiestminds.projectallocationsystem.entity.UserEntity;

/**
 * @author rasool.shaik
 *
 */
@Repository
public class UserDaoImpl extends AbstractDaoImpl<UserEntity, String> implements UserDAO {

	protected UserDaoImpl() {
		super(UserEntity.class);
	}

}
