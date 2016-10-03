package com.happiestminds.projectallocationsystem.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.happiestminds.projectallocationsystem.entity.GroupRightsEntity;
import com.happiestminds.projectallocationsystem.entity.ModuleEntity;
import com.happiestminds.projectallocationsystem.entity.UserGroupEntity;

/**
 * @author rasool.shaik
 * 
 */
public interface UserGroupDAO extends AbstractDAO<UserGroupEntity, String> {
	List<ModuleEntity> fetchOperationsByGroup(String groupId) throws HibernateException;

	List<GroupRightsEntity> fetchGroupRightsByGroup(String groupId) throws HibernateException;

	List<UserGroupEntity> fetchAllUsersWithGroups() throws HibernateException;

}
