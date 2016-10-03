package com.happiestminds.projectallocationsystem.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.happiestminds.projectallocationsystem.Dto.ProjectDto;
import com.happiestminds.projectallocationsystem.Dto.ProjectOptionDto;
import com.happiestminds.projectallocationsystem.Dto.batch.ProjectListDto;

/**
 * @author rasool.shaik
 * 
 */
public interface ProjectService {
	@Transactional(readOnly = false)
	void addProject(ProjectDto projectDto)throws ParseException;

	@Transactional(readOnly = false)
	void updateProject(ProjectDto projectDto)throws ParseException;

	@Transactional
	void deleteProject(ProjectDto projectDto);

	@Transactional(readOnly = true)
	ProjectListDto fetchAllProjectsWithCustomers(int jtStartIndex, int jtPageSize, String jtSorting) throws ParseException;

	@Transactional(readOnly = true)
	int getProjectsCount();

	@Transactional(readOnly = true)
	List<ProjectOptionDto> getProjectsAsOptions();
	
}
