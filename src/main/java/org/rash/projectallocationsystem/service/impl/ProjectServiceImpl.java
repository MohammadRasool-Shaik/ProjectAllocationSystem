package org.rash.projectallocationsystem.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.rash.projectallocationsystem.Dto.ProjectDto;
import org.rash.projectallocationsystem.Dto.ProjectOptionDto;
import org.rash.projectallocationsystem.Dto.StatusDto;
import org.rash.projectallocationsystem.Dto.batch.ProjectListDto;
import org.rash.projectallocationsystem.dao.ProjectDAO;
import org.rash.projectallocationsystem.entity.CustomerEntity;
import org.rash.projectallocationsystem.entity.ProjectEntity;
import org.rash.projectallocationsystem.enumerator.StatusCodeEnum;
import org.rash.projectallocationsystem.service.ProjectService;
import org.rash.projectallocationsystem.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDAO projectDAO;

	public ProjectServiceImpl() {
		super();
	}

	@Override
	public void addProject(ProjectDto project) throws ParseException {
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		ProjectEntity projectEntity = null;
		projectEntity = projectDAO.findById(project.getProjectID());
		boolean dateValidation = (DateUtil.getDate(project.getStartDate()).before(DateUtil.getDate(project.getExpectedEndDate()))) ? true : false;
		if (projectEntity == null && dateValidation) {

			project.setProjectID(project.getProjectID().toUpperCase());
			projectEntity = new ProjectEntity();
			BeanUtils.copyProperties(project, projectEntity);
			projectEntity.setStartDate(DateUtil.getDate(project.getStartDate()));
			projectEntity.setExpectedEndDate(DateUtil.getDate(project.getExpectedEndDate()));
			CustomerEntity customer = new CustomerEntity();
			customer.setCustomerID(project.getCustomerId());
			projectEntity.setCustomer(customer);
			projectDAO.save(projectEntity);
			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("Project Added Successfully");
		} else {
			statusDto.setStatusMessage("Project with same ID Already Exit, Try Another?");
			if (!dateValidation) {
				statusDto.setStatusMessage("Please enter an end date after the start date");
			}
			// throwAlredyExistValidationError(SkillSetDto.class.getSimpleName(),
			// skillSetDto.getSkillID());
		}
		project.setStatusDto(statusDto);

	}

	@Override
	public void updateProject(ProjectDto project) throws ParseException {
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		boolean dateValidation = (DateUtil.getDate(project.getStartDate()).before(DateUtil.getDate(project.getExpectedEndDate()))) ? true : false;
		if (project != null && dateValidation) {
			ProjectEntity projectEntity = new ProjectEntity();
			BeanUtils.copyProperties(project, projectEntity);
			projectEntity.setStartDate(DateUtil.getDate(project.getStartDate()));
			projectEntity.setExpectedEndDate(DateUtil.getDate(project.getExpectedEndDate()));
			CustomerEntity customer = new CustomerEntity();
			customer.setCustomerID(project.getCustomerId());
			projectEntity.setCustomer(customer);
			projectEntity = projectDAO.update(projectEntity);
			if (projectEntity != null) {
				statusDto.setStatusCode(StatusCodeEnum.OK);
				statusDto.setStatusMessage("Successfully updated Project");
			}
		} else {
			if (!dateValidation) {
				statusDto.setStatusMessage("Please enter an end date after the start date");
			}
		}
		project.setStatusDto(statusDto);

	}

	@Override
	public void deleteProject(ProjectDto proejct) {
		int noOfEntiesDeleted = 0;
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		noOfEntiesDeleted = projectDAO.deleteById("ProjectEntity", proejct.getProjectID());
		ProjectDto proejctTemp = new ProjectDto();
		BeanUtils.copyProperties(proejctTemp, proejct);
		if (noOfEntiesDeleted > 0) {
			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("Successfully Deleted Project");
		}
		proejct.setStatusDto(statusDto);
	}

	@Override
	public ProjectListDto fetchAllProjectsWithCustomers(int startIndex, int pageSize, String sortvar) throws ParseException {
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		ProjectListDto projects = new ProjectListDto();
		List<ProjectEntity> projectList = projectDAO.fetchAllProjectWithCustomers(startIndex, pageSize, sortvar);
		if (!projectList.isEmpty()) {
			for (ProjectEntity project : projectList) {
				ProjectDto projectDto = new ProjectDto();
				BeanUtils.copyProperties(project, projectDto);

				projectDto.setStartDate(DateUtil.parseDate(project.getStartDate()));
				projectDto.setExpectedEndDate(DateUtil.parseDate(project.getExpectedEndDate()));
				CustomerEntity customer = project.getCustomer();
				projectDto.setCustomerId(customer.getCustomerID());
				projects.getProjects().add(projectDto);
			}
			statusDto.setStatusCode(StatusCodeEnum.OK);
		} else {
			statusDto.setStatusMessage("Projects are not available, Contact Administrator");
		}
		projects.setStatusDto(statusDto);
		return projects;
	}

	@Override
	public List<ProjectOptionDto> getProjectsAsOptions() {
		List<ProjectOptionDto> pResponse = new ArrayList<ProjectOptionDto>();
		Collection<ProjectEntity> projectList = projectDAO.findAll();
		for (ProjectEntity project : projectList) {
			ProjectOptionDto projectDto = new ProjectOptionDto();
			projectDto.setValue(project.getProjectID());
			projectDto.setDisplayText(project.getProjectName());
			pResponse.add(projectDto);
		}
		return pResponse;
	}

	@Override
	public int getProjectsCount() {
		return projectDAO.getProjectsCount();
	}

}
