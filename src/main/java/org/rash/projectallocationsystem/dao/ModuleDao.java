package org.rash.projectallocationsystem.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.rash.projectallocationsystem.entity.ModuleEntity;
/**
 * @author rasool.shaik
 *
 */
public interface ModuleDao extends AbstractDAO<ModuleEntity, String> {

	List<String> fetchAllModuleIds() throws HibernateException;

}
