package org.rash.projectallocationsystem.controller;

import java.text.ParseException;
import java.util.List;

import org.rash.projectallocationsystem.Dto.ProjectDto;
import org.rash.projectallocationsystem.Dto.ProjectOptionDto;
import org.rash.projectallocationsystem.Dto.batch.ProjectListDto;
import org.rash.projectallocationsystem.response.ProjectListResponse;
import org.rash.projectallocationsystem.response.ProjectOptionsResponse;
import org.rash.projectallocationsystem.response.ProjectResponse;
import org.rash.projectallocationsystem.service.CustomerService;
import org.rash.projectallocationsystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author rasool.shaik
 * 
 */
@Controller
public class ProjectController extends BaseController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private CustomerService customerService;

	/**
	 * 
	 */
	public ProjectController() {
	}

	@RequestMapping(value = "/prjctm", method = RequestMethod.GET)
	public String showProjects(Model model) {
		fetchOperationsByUserGroupForMenu(model);
		return "projectsMang";
	}

	@RequestMapping(value = "/prjctm/fetchAllProjects", method = RequestMethod.POST)
	public @ResponseBody
	ProjectListResponse fetchAllProject(@RequestParam int jtStartIndex, @RequestParam int jtPageSize, @RequestParam(required = false) String jtSorting) throws ParseException {
		ProjectListResponse prListResponse = new ProjectListResponse();

		ProjectListDto projects = projectService.fetchAllProjectsWithCustomers(jtStartIndex, jtPageSize, jtSorting);

		prListResponse.setTotalRecordCount(projectService.getProjectsCount());
		prListResponse.setProjects(projects.getProjects());
		prListResponse.setResult(projects.getStatusDto().getStatusCode().getMsg());
		prListResponse.setMessage(projects.getStatusDto().getStatusMessage());

		// model.addAttribute("customers", customerService.fetchCustomers());
		return prListResponse;
	}

	@RequestMapping(value = "/prjctm/projectaction", method = RequestMethod.POST)
	public @ResponseBody
	ProjectResponse projectActions(@RequestParam String action, @ModelAttribute ProjectDto project) throws ParseException {
		ProjectResponse projectResponse = new ProjectResponse();
		ProjectDto projectResult = new ProjectDto();
		switch (action.toLowerCase()) {
		case "save":
			projectService.addProject(project);
			projectResult = project;
			break;
		case "update":
			projectService.updateProject(project);
			projectResult = project;
			break;
		case "delete":
			projectService.deleteProject(project);
			projectResult = project;
			break;
		}

		projectResponse.setProject(projectResult);
		projectResponse.setResult(projectResult.getStatusDto().getStatusCode().getMsg());
		projectResponse.setMessage(projectResult.getStatusDto().getStatusMessage());
		// model.addAttribute("customers", customerService.fetchCustomers());
		return projectResponse;
	}

	@RequestMapping(value = "/prjctm/getProjectOptions", method = RequestMethod.POST)
	public @ResponseBody
	ProjectOptionsResponse getProjectOptions() {
		ProjectOptionsResponse projectsAsOptions;
		List<ProjectOptionDto> projects;
		try {
			projects = projectService.getProjectsAsOptions();
			projectsAsOptions = new ProjectOptionsResponse("OK", projects);
		} catch (Exception e) {
			projectsAsOptions = new ProjectOptionsResponse("ERROR", e.getMessage());
		}
		return projectsAsOptions;
	}

}
