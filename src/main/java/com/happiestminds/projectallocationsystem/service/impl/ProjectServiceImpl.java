package com.happiestminds.projectallocationsystem.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happiestminds.projectallocationsystem.dao.ProjectDAO;
import com.happiestminds.projectallocationsystem.entity.ProjectEntity;
import com.happiestminds.projectallocationsystem.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	private static Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class.getSimpleName());
	@Autowired
	private ProjectDAO projectDAO;

	public ProjectServiceImpl() {
		super();
	}

	@Override
	public boolean addProject(ProjectEntity project) {
		boolean isProjectAdded = false;
		ProjectEntity projectEntity = null;
		try {
			projectEntity = projectDAO.findById(project.getProjectID());
			if (projectEntity == null) {
				project.setProjectID(project.getProjectID().toUpperCase());
				projectDAO.save(project);
				isProjectAdded = true;
			}
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED  WHILE SAVING PROJECT INTO PROJECTENTIY" + hex.getMessage(), hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED  WHILE SAVING PROJECT INTO PROJECTENTIY" + ex.getMessage(), ex);
		}

		return isProjectAdded;
	}

	@Override
	public boolean updateProject(ProjectEntity ProjectEntity) {
		boolean isProjectUpdated = false;
		try {
			projectDAO.update(ProjectEntity);
			isProjectUpdated = true;
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED  WHILE UPDATING PROJECT INTO PROJECTENTIY" + hex.getMessage(), hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED  WHILE UPDATING PROJECT INTO PROJECTENTIY" + ex.getMessage(), ex);
		}

		return isProjectUpdated;
	}

	@Override
	public boolean deleteProject(ProjectEntity ProjectEntity) {
		boolean isProjectDeleted = false;
		try {
			projectDAO.deleteById("ProjectEntity", ProjectEntity.getProjectID());
			isProjectDeleted = true;
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED WHILE DELTEING PROJECT PROJECTENTIY" + hex.getMessage(), hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED  WHILE DELTEING PROJECT  PROJECTENTIY" + ex.getMessage(), ex);
		}
		return isProjectDeleted;
	}

	@Override
	public List<ProjectEntity> fetchAllProjectWithCustomers() {
		List<ProjectEntity> customers = null;
		try {
			customers = (List<ProjectEntity>) projectDAO.fetchAllProjectWithCustomers();
		} catch (HibernateException hex) {
			logger.error("HIBERNATE EXCEPTION OCCURED WHILE FETCHING PROJECTS FROM PROJECTENTIY" + hex.getMessage(), hex);
		} catch (Exception ex) {
			logger.error("EXCEPTION OCCURED  WHILE FETCHING PROJECTS FROM PROJECTENTIY" + ex.getMessage(), ex);
		}
		return customers;
	}

}
