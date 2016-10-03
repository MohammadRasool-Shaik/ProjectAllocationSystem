package com.happiestminds.projectallocationsystem.dao.impl;

import com.happiestminds.projectallocationsystem.dao.ProjectDAO;
import com.happiestminds.projectallocationsystem.entity.ProjectEntity;

/**
 * @author rasool.shaik
 *
 */
public class ProjectDaoImpl extends AbstractDaoImpl<ProjectEntity, String> implements ProjectDAO {

	protected ProjectDaoImpl(){
		super(ProjectEntity.class);
	}
}
