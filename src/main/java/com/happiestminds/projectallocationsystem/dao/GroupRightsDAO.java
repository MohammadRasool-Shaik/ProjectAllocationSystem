package com.happiestminds.projectallocationsystem.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.happiestminds.projectallocationsystem.entity.GroupRightsEntity;
import com.happiestminds.projectallocationsystem.entity.GroupRightsPK;

/**
 * @author rasool.shaik
 * 
 */
public interface GroupRightsDAO extends AbstractDAO<GroupRightsEntity, GroupRightsPK> {

	public void revokeGroupRightsFromGroup(String groupId, String operationId) throws HibernateException;

	List<String> fetchGroupRightsByGroup(String groupId) throws HibernateException;

}
