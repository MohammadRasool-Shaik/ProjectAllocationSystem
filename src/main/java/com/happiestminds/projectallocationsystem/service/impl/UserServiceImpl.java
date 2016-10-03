package com.happiestminds.projectallocationsystem.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.happiestminds.projectallocationsystem.dao.GroupRightsDAO;
import com.happiestminds.projectallocationsystem.dao.UserDAO;
import com.happiestminds.projectallocationsystem.entity.UserEntity;
import com.happiestminds.projectallocationsystem.entity.UserGroupEntity;
import com.happiestminds.projectallocationsystem.service.UserService;

/**
 * @author rasool.shaik
 * 
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getSimpleName());

	@Autowired
	private UserDAO userDao;

	@Autowired
	private GroupRightsDAO groupRightsDAO;

	/**
	 * 
	 */
	public UserServiceImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.happiestminds.projectallocationsystem.service.UserService#addUser
	 * (com.happiestminds.projectallocationsystem.entity.UserEntity)
	 */
	@Override
	public boolean addUser(UserEntity user) {
		boolean isUserAdded = false;
		try {
			UserEntity userEntity = userDao.findById(user.getUserId());
			if (userEntity == null) {
				user.setUserId(user.getUserId().toUpperCase());
				user.setEmailId(user.getEmailId().toLowerCase());
				UserGroupEntity userGroup = new UserGroupEntity();
				userGroup.setGroupId("UID");
				user.setUserGroup(userGroup);
				String password = user.getPassword();
				user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
				userDao.save(user);
				isUserAdded = true;
			}
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED WHILE ADDING USER IN ENTITY", hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED WHILE ADDING USER IN ENTITY", ex);
		}

		return isUserAdded;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.happiestminds.projectallocationsystem.service.UserService#updateUser
	 * (com.happiestminds.projectallocationsystem.entity.UserEntity)
	 */
	@Override
	public void updateUser(UserEntity user, String groupId) {
		try {
			user.setEmailId(user.getEmailId().toLowerCase());
			userDao.updateUserbyId(user, groupId);
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED WHILE UPDATING USER IN ENTITY", hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED WHILE UPDATIN USER IN ENTITY", ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.happiestminds.projectallocationsystem.service.UserService#deleteUser
	 * (com.happiestminds.projectallocationsystem.entity.UserEntity)
	 */
	@Override
	public void deleteUser(UserEntity user) {

		try {
			userDao.deleteById("UserEntity", user.getUserId());
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED WHILE DELETE USER IN ENTITY", hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED WHILE DELETE USER IN ENTITY", ex);
		}
	}

	@Override
	public List<UserEntity> getAllUsersWithGroups() {
		List<UserEntity> users = null;
		try {
			users = (List<UserEntity>) userDao.getAllUsers();
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED WHILE FETCHING USERENTITY", hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED WHILE FETCHING USERENTITY", ex);
		}
		return users;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = userDao.findByUserName(username);
		if (user != null) {
			String userId = user.getUserId();
			String password = user.getPassword();
			// additional information on the security object
			boolean enabled = user.getAccountStatus() == 1 ? true : false;
			boolean accountNonExpired = user.getAccountStatus() == 1 ? true : false;
			boolean credentialsNonExpired = user.getAccountStatus() == 1 ? true : false;
			boolean accountNonLocked = user.getAccountStatus() == 1 ? true : false;

			// Let's populate user operations/featues
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

			List<String> operationIds = groupRightsDAO.fetchGroupRightsByGroup(user.getUserGroup().getGroupId());

			for (String operation : operationIds) {
				authorities.add(new SimpleGrantedAuthority(operation));
			}

			return new com.happiestminds.projectallocationsystem.controller.UserDetails(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
					authorities, userId);

		} else {
			throw new UsernameNotFoundException("User Not Found!!!");
		}
	}
}
