package org.rash.projectallocationsystem.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.rash.projectallocationsystem.entity.GroupRightsEntity;
import org.rash.projectallocationsystem.entity.ModuleEntity;
import org.rash.projectallocationsystem.entity.UserGroupEntity;

/**
 * @author rasool.shaik
 * 
 */
public interface UserGroupDAO extends AbstractDAO<UserGroupEntity, String> {
	List<ModuleEntity> fetchOperationsByGroup(String groupId) throws HibernateException;

	List<GroupRightsEntity> fetchGroupRightsByGroup(String groupId) throws HibernateException;

	List<UserGroupEntity> fetchAllUsersWithGroups(int startIndex, int pageSize, String sortVar) throws HibernateException;

	int getCountAllUserGroups() throws HibernateException;

	List<UserGroupEntity> fetchAllUsersGroups(int startIndex, int pageSize, String sortVar) throws HibernateException;

}
