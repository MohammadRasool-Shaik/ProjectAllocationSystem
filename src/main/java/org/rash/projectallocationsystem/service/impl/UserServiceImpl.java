package org.rash.projectallocationsystem.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.rash.projectallocationsystem.Dto.StatusDto;
import org.rash.projectallocationsystem.Dto.UserDto;
import org.rash.projectallocationsystem.Dto.UserGroupDto;
import org.rash.projectallocationsystem.Dto.batch.UserGroupListDto;
import org.rash.projectallocationsystem.Dto.batch.UserListDto;
import org.rash.projectallocationsystem.dao.GroupRightsDAO;
import org.rash.projectallocationsystem.dao.UserDAO;
import org.rash.projectallocationsystem.dao.UserGroupDAO;
import org.rash.projectallocationsystem.entity.UserEntity;
import org.rash.projectallocationsystem.entity.UserGroupEntity;
import org.rash.projectallocationsystem.enumerator.StatusCodeEnum;
import org.rash.projectallocationsystem.enumerator.UserStatus;
import org.rash.projectallocationsystem.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * @author rasool.shaik
 * 
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private GroupRightsDAO groupRightsDAO;

	@Autowired
	private UserGroupDAO userGroupDAO;

	/**
	 * 
	 */
	public UserServiceImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rash.projectallocationsystem.service.UserService#addUser
	 * (org.rash.projectallocationsystem.entity.UserEntity)
	 */
	@Override
	public UserDto addUser(UserDto user) {
		UserDto userResponse = new UserDto();
		StatusDto statusDto = new StatusDto();
		UserEntity userEntity = new UserEntity();
		String userName = user.getUserName();
		userEntity.setUserName(userName);
		if (userName.indexOf('@') != -1) {
			if (!userName.substring(userName.indexOf('@')).equalsIgnoreCase("@gmail.com")) {
				userName = userName + "@gmail.com";
			}
		} else {
			userName = userName + "@gmail.com";
		}
		user.setEmailId(userName.toLowerCase());
		userEntity.setEmailId(userName);
		userEntity.setUserId(user.getUserId());
		String password = user.getPassword();
		userEntity.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

		userEntity.setAccountStatus(UserStatus.value(Integer.valueOf(user.getAccountStatus())));

		UserGroupEntity userGroupEntity = new UserGroupEntity();
		userGroupEntity.setGroupId("UID");
		userEntity.setUserGroup(userGroupEntity);

		userDao.save(userEntity);

		BeanUtils.copyProperties(user, userResponse);
		statusDto.setStatusCode(StatusCodeEnum.SUCCESS);
		statusDto.setStatusMessage("Hi " + user.getUserName() + " You are Successfully Registered");

		userResponse.setStatusDto(statusDto);
		return userResponse;
	}

	@Deprecated
	public UserDto addUser1(UserDto user) {
		UserDto userResponse = new UserDto();
		StatusDto statusDto = new StatusDto();
		List<UserEntity> userList = userDao.findByUserNameOREmail(user);
		if (userList == null) {
			UserEntity userEntity = new UserEntity();
			userEntity.setUserName(user.getUserName());
			userEntity.setEmailId(user.getEmailId().toLowerCase());
			String password = user.getPassword();
			userEntity.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));

			UserGroupEntity userGroupEntity = new UserGroupEntity();
			userGroupEntity.setGroupId("UID");
			userEntity.setUserGroup(userGroupEntity);

			userDao.save(userEntity);

			BeanUtils.copyProperties(user, userResponse);
			statusDto.setStatusCode(StatusCodeEnum.SUCCESS);
			statusDto.setStatusMessage("Hi " + user.getUserName() + "You are Successfully Registered");
		} else {
			for (Iterator<UserEntity> iterator = userList.iterator(); iterator.hasNext();) {
				UserEntity userEntity = (UserEntity) iterator.next();
				if (userEntity.getUserName().equals(user.getUserName())) {
					statusDto.setStatusCode(StatusCodeEnum.CREATEACCOUNTFAILUREMESSAGEUSER);
					break;
				} else if (userEntity.getEmailId().equals(user.getEmailId())) {
					statusDto.setStatusCode(StatusCodeEnum.CREATEACCOUNTFAILUREMESSAGEEMAIL);
					break;
				}
			}
		}
		userResponse.setStatusDto(statusDto);
		return userResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rash.projectallocationsystem.service.UserService#updateUser
	 * (org.rash.projectallocationsystem.entity.UserEntity)
	 */
	@Override
	public void updateUser(UserDto user) {
		int noOfEntiesUpdated = 0;
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		Integer accountStatus = user.getAccountStatus();
		UserEntity userEntity = userDao.findByUserName(user.getUserName());
		// If user doen't have permission to update account status
		if (accountStatus == null) {
			accountStatus = userEntity.getAccountStatus().getValue();
			user.setAccountStatus(accountStatus);
		}
		user.setEmailId(userEntity.getEmailId());
		noOfEntiesUpdated = userDao.updateUserbyName(user);
		statusDto.setStatusCode(StatusCodeEnum.OK);
		if (noOfEntiesUpdated > 0) {
			statusDto.setStatusMessage("Successfully updated user");
		}
		user.setStatusDto(statusDto);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rash.projectallocationsystem.service.UserService#deleteUser
	 * (org.rash.projectallocationsystem.entity.UserEntity)
	 */
	@Override
	public void deleteUser(UserDto user, String userId) {
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		if (user.getUserName() != userId) {
			int noOfEntiesDeleted = userDao.deleteUserByName(user.getUserName());
			UserDto userTemp = new UserDto();
			BeanUtils.copyProperties(userTemp, user);
			if (noOfEntiesDeleted > 0) {
				statusDto.setStatusCode(StatusCodeEnum.OK);
				statusDto.setStatusMessage("Successfully Deleted user");
			}
		} else {
			statusDto.setStatusMessage("You can not delete yourself");
		}
		user.setStatusDto(statusDto);
	}

	public UserListDto getAllUsersWithGroups1(UserGroupListDto groups, int startIndex, int pageSize, String sortVar) {
		UserListDto users = new UserListDto();
		StatusDto statusDto = new StatusDto();
		List<UserGroupEntity> usersWithGroups = userGroupDAO.fetchAllUsersWithGroups(startIndex, pageSize, sortVar);
		for (UserGroupEntity userWithGroup : usersWithGroups) {
			for (UserEntity userEntity : userWithGroup.getUsers()) {
				UserDto user = new UserDto();
				BeanUtils.copyProperties(userEntity, user);
				user.setUserGroupId(userEntity.getUserGroup().getGroupId());
				users.getUsers().add(user);
			}
			UserGroupDto userGroupDto = new UserGroupDto();
			BeanUtils.copyProperties(userWithGroup, userGroupDto);
			groups.getUserGroups().add(userGroupDto);
		}
		statusDto.setStatusCode(StatusCodeEnum.OK);
		users.setStatusDto(statusDto);
		return users;
	}

	@Override
	public UserListDto getAllUsersWithGroups(UserGroupListDto groups, int startIndex, int pageSize, String sortVar) {
		UserListDto users = new UserListDto();
		StatusDto statusDto = new StatusDto();
		List<UserEntity> allUsers = userDao.getAllUsers(startIndex, pageSize, sortVar);
		for (UserEntity userEntity : allUsers) {
			UserDto user = new UserDto();
			BeanUtils.copyProperties(userEntity, user);
			user.setUserGroupId(userEntity.getUserGroup().getGroupId());
			user.setAccountStatus(userEntity.getAccountStatus().getValue());
			users.getUsers().add(user);
		}
		statusDto.setStatusCode(StatusCodeEnum.OK);
		users.setStatusDto(statusDto);
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
		if (username.indexOf('@') != -1) {
			username = username.substring(0, username.indexOf('@'));
		}
		UserEntity user = userDao.findByUserName(username.toLowerCase());
		if (user != null) {
			String userId = user.getUserId();
			String password = user.getPassword();
			// additional information on the security object
			boolean enabled = user.getAccountStatus().getValue() == 1 ? true : false;
			boolean accountNonExpired = user.getAccountStatus().getValue() == 1 ? true : false;
			boolean credentialsNonExpired = user.getAccountStatus().getValue() == 1 ? true : false;
			boolean accountNonLocked = user.getAccountStatus().getValue() == 1 ? true : false;

			// Let's populate user operations/featues
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

			List<String> operationIds = groupRightsDAO.fetchGroupRightsByGroup(user.getUserGroup().getGroupId());

			for (String operation : operationIds) {
				authorities.add(new SimpleGrantedAuthority(operation));
			}

			return new org.rash.projectallocationsystem.controller.UserDetails(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
					authorities, userId);

		} else {
			throw new UsernameNotFoundException("User Not Found!!!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.rash.projectallocationsystem.service.UserService#getUser
	 * (java.lang.String)
	 */
	@Override
	public UserDto getUser(String userName) {
		UserDto user = new UserDto();
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		UserEntity userEntity = userDao.findByUserName(userName);
		if (userEntity != null) {
			user.setUserId(userEntity.getUserId());
			user.setUserName(userEntity.getUserName());
			statusDto.setStatusCode(StatusCodeEnum.SUCCESS);
			statusDto.setStatusMessage("Someone already has that username. Try another?");
			user.setStatusDto(statusDto);
		}
		return user;
	}

	@Override
	public int getAllUserCount() {
		return userDao.getAllUsersCount();
	}

}
