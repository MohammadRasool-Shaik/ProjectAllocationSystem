package com.happiestminds.projectallocationsystem.dao;

import java.util.List;

import com.happiestminds.projectallocationsystem.entity.EmployeeEntity;
import com.happiestminds.projectallocationsystem.entity.UserEntity;

/**
 * @author rasool.shaik
 * 
 */
public interface EmployeeDAO extends AbstractDAO<EmployeeEntity, String> {

	List<EmployeeEntity> fetchAllEmployeesWithAllInfo();

	List<EmployeeEntity> fetchAllEmployees(int startIndex, int pageSize, String sortVar);

	int getAllEmployeeCount();

	EmployeeEntity findEmployeeByName(String eName);

	List<EmployeeEntity> fetchEmployeesByKeyWords(String keyword);

	List<UserEntity> fetchPracticeHeadsBySkill(String skillId);

	List<EmployeeEntity> fetchAllPracticeHeads(String keyword);

}
