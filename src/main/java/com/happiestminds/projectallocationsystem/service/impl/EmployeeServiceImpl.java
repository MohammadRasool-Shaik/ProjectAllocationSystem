package com.happiestminds.projectallocationsystem.service.impl;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happiestminds.projectallocationsystem.Dto.EmployeeDto;
import com.happiestminds.projectallocationsystem.Dto.StatusDto;
import com.happiestminds.projectallocationsystem.Dto.batch.EmployeeListDto;
import com.happiestminds.projectallocationsystem.dao.EmployeeDAO;
import com.happiestminds.projectallocationsystem.entity.EmployeeEntity;
import com.happiestminds.projectallocationsystem.entity.ProjectEntity;
import com.happiestminds.projectallocationsystem.entity.SkillSetEntity;
import com.happiestminds.projectallocationsystem.enumerator.EmployeeStatus;
import com.happiestminds.projectallocationsystem.enumerator.StatusCodeEnum;
import com.happiestminds.projectallocationsystem.service.EmployeeService;
import com.happiestminds.projectallocationsystem.util.DateUtil;

/**
 * @author rasool.shaik
 * 
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	/**
	 * 
	 */
	public EmployeeServiceImpl() {
	}

	@Override
	public void addEmployee(EmployeeDto employee) throws ParseException {
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		EmployeeEntity employeeEntity = employeeDAO.findById(employee.getEmployeeId());
		boolean dateValidation = DateUtil.isEndDateAfterStartDate(employee.getAssignedDate(), employee.getEndDate());
		if (employeeEntity == null && dateValidation) {
			employee.setEmployeeId(employee.getEmployeeId().toUpperCase());
			employeeEntity = new EmployeeEntity();
			BeanUtils.copyProperties(employee, employeeEntity);

			employeeEntity.setAssignedDate(DateUtil.getDate(employee.getAssignedDate()));
			employeeEntity.setEndDate(DateUtil.getDate(employee.getEndDate()));

			SkillSetEntity skillSetEntity = new SkillSetEntity();
			skillSetEntity.setSkillID(employee.getSkillSetId());
			employeeEntity.setSkillSetEntity(skillSetEntity);

			ProjectEntity projectEntity = new ProjectEntity();
			projectEntity.setProjectID(employee.getProjectId());
			employeeEntity.setProjectEntity(projectEntity);

			employeeEntity.setEmpStatus(EmployeeStatus.value(Integer.valueOf(employee.getEmpStatus())));

			employeeDAO.save(employeeEntity);
			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("Employee Added Successfully");
		} else {
			statusDto.setStatusMessage("Employee with same ID Already Exit, Try Another?");
			if (!dateValidation) {
				statusDto.setStatusMessage("Please enter an End-Date after the Start-Date");
			}
			// throwAlredyExistValidationError(SkillSetDto.class.getSimpleName(),
			// skillSetDto.getSkillID());
		}
		employee.setStatusDto(statusDto);
	}

	@Override
	public void updateEmployee(EmployeeDto employee) throws ParseException {
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		boolean dateValidation = DateUtil.isEndDateAfterStartDate(employee.getAssignedDate(), employee.getEndDate());
		EmployeeEntity employeeEntity = new EmployeeEntity();
		if (employee != null && dateValidation) {
			employeeEntity.setEmpStatus(EmployeeStatus.value(Integer.valueOf(employee.getEmpStatus())));
			BeanUtils.copyProperties(employee, employeeEntity);

			employeeEntity.setAssignedDate(DateUtil.getDate(employee.getAssignedDate()));
			employeeEntity.setEndDate(DateUtil.getDate(employee.getEndDate()));

			SkillSetEntity skillSetEntity = new SkillSetEntity();
			skillSetEntity.setSkillID(employee.getSkillSetId());
			employeeEntity.setSkillSetEntity(skillSetEntity);

			ProjectEntity projectEntity = new ProjectEntity();
			projectEntity.setProjectID(employee.getProjectId());
			employeeEntity.setProjectEntity(projectEntity);

			employeeEntity = employeeDAO.update(employeeEntity);

			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("Successfully updated Employee");
		} else {
			statusDto.setStatusMessage("Un-Success to updated Employee");
			if (!dateValidation) {
				statusDto.setStatusMessage("Please enter an end date after the start date");
			}
		}
		employee.setStatusDto(statusDto);

	}

	@Override
	public void deleteEmployee(EmployeeDto employee) {
		int noOfEntiesDeleted = 0;
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		noOfEntiesDeleted = employeeDAO.deleteById("EmployeeEntity", employee.getEmployeeId());
		EmployeeDto employeeTemp = new EmployeeDto();
		BeanUtils.copyProperties(employeeTemp, employee);
		if (noOfEntiesDeleted > 0) {
			statusDto.setStatusCode(StatusCodeEnum.OK);
			statusDto.setStatusMessage("Successfully Deleted Employee");
		}
		employee.setStatusDto(statusDto);

	}

	@Override
	public EmployeeListDto fetchEmployees(int startIndex, int pageSize, String sortVar) throws ParseException {
		EmployeeListDto employees = new EmployeeListDto();
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		Collection<EmployeeEntity> employeeList = employeeDAO.fetchAllEmployees(startIndex, pageSize, sortVar);
		if (!employeeList.isEmpty()) {
			for (EmployeeEntity employeeEntity : employeeList) {

				EmployeeDto employee = new EmployeeDto();
				BeanUtils.copyProperties(employeeEntity, employee);

				employee.setProjectId(employeeEntity.getProjectEntity().getProjectID());
				employee.setSkillSetId(employeeEntity.getSkillSetEntity().getSkillID());

				employee.setAssignedDate(DateUtil.parseDate(employeeEntity.getAssignedDate()));
				employee.setEndDate(DateUtil.parseDate(employeeEntity.getEndDate()));

				employee.setEmpStatus(employeeEntity.getEmpStatus().getValue());

				employees.getEmployees().add(employee);
			}
			statusDto.setStatusCode(StatusCodeEnum.OK);
		} else {
			statusDto.setStatusMessage("Employees are not available, Contact Administrator");
		}
		employees.setStatusDto(statusDto);

		return employees;
	}

	@Override
	public int getAllEmployeeCount() {
		return employeeDAO.getAllEmployeeCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happiestminds.projectallocationsystem.service.EmployeeService#
	 * fetchEmployees()
	 */
	@Override
	public EmployeeListDto fetchEmployees() {

		EmployeeListDto employees = new EmployeeListDto();
		StatusDto statusDto = new StatusDto(StatusCodeEnum.ERROR);
		Collection<EmployeeEntity> employeeList = employeeDAO.findAll();
		if (!employeeList.isEmpty()) {
			for (EmployeeEntity employeeEntity : employeeList) {
				EmployeeDto employee = new EmployeeDto();
				BeanUtils.copyProperties(employeeEntity, employee);

				employee.setProjectId(employeeEntity.getProjectEntity().getProjectID());
				employee.setSkillSetId(employeeEntity.getSkillSetEntity().getSkillID());

				employee.setEmpStatus(employeeEntity.getEmpStatus().getValue());

				employees.getEmployees().add(employee);
			}
			statusDto.setStatusCode(StatusCodeEnum.OK);
		} else {
			statusDto.setStatusMessage("Employees are not available, Contact Administrator");
		}
		employees.setStatusDto(statusDto);

		return employees;
	}

	@Override
	public EmployeeDto findEmployeeByName(String name) {
		EmployeeDto employeeDto = new EmployeeDto();
		EmployeeEntity employee = employeeDAO.findEmployeeByName(name);
		BeanUtils.copyProperties(employee, employeeDto);
		return employeeDto;
	}

	@Override
	public JSONArray fetchEmployeesByKeyWords(String keyword) {
		JSONArray employeesDto = new JSONArray();
		List<EmployeeEntity> employees = employeeDAO.fetchEmployeesByKeyWords(keyword);
//		List<EmployeeEntity> practiceHeads = employeeDAO.fetchAllPracticeHeads();
		for (EmployeeEntity employee : employees) {
			JSONObject employeeDto = new JSONObject();
			employeeDto.put("employeeId", employee.getEmployeeId());
			employeeDto.put("employeeName", employee.getEmployeeName());
			employeesDto.add(employeeDto);
		}
		return employeesDto;
	}

}
