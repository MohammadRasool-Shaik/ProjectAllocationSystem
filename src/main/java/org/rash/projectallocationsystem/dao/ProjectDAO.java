package org.rash.projectallocationsystem.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.rash.projectallocationsystem.entity.ProjectEntity;

/**
 * @author rasool.shaik
 *
 */
public interface ProjectDAO extends AbstractDAO<ProjectEntity, String> {

	List<String> fetchAllProjectIds() throws HibernateException;

	List<ProjectEntity> fetchAllProjectWithCustomers(int startIndex, int pageSize, String sortvar)throws HibernateException;

	int getProjectsCount() throws HibernateException;

}
