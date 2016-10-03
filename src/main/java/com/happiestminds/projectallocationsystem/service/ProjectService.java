package com.happiestminds.projectallocationsystem.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.entity.ProjectEntity;

/**
 * @author rasool.shaik
 *
 */
public interface ProjectService {
	@Transactional(readOnly=false)
	boolean addProject(ProjectEntity ProjectEntity);

	@Transactional(readOnly=false)
	boolean updateProject(ProjectEntity ProjectEntity);

	@Transactional
	boolean deleteProject(ProjectEntity ProjectEntity);

	@Transactional(readOnly=true)
	List<ProjectEntity> fetchAllProjectWithCustomers();
}
