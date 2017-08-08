package org.rash.projectallocationsystem.dao;

import java.util.List;

import org.rash.projectallocationsystem.entity.EmployeeEntity;

/**
 * @author rasool.shaik
 * 
 */
public interface EmployeeDAO extends AbstractDAO<EmployeeEntity, String> {

	List<EmployeeEntity> fetchAllEmployeesWithAllInfo();

	List<EmployeeEntity> fetchAllEmployees(int startIndex, int pageSize, String sortVar);

	int getAllEmployeeCount();

	EmployeeEntity findEmployeeByName(String eName);

}
