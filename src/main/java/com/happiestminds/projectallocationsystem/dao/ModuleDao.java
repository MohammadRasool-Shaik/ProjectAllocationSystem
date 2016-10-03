package com.happiestminds.projectallocationsystem.dao;

import com.happiestminds.projectallocationsystem.entity.ModuleEntity;
/**
 * @author rasool.shaik
 *
 */
public interface ModuleDao extends AbstractDAO<ModuleEntity, String> {

	public void saveInitialData(ModuleEntity module);
}
