package com.example.HibernateSpringBoot.employee.dao;

import java.util.List;

import com.example.HibernateSpringBoot.employee.dto.Employee;
import com.example.HibernateSpringBoot.employee.dto.EmployeeDTO;

public interface EmployeeDao {
	
	public List<Employee> getAllEmployees();
	
	public List<EmployeeDTO> getEmpByDeptName(String departmentName);
	
}
