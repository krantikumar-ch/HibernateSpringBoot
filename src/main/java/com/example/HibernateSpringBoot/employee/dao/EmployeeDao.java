package com.example.HibernateSpringBoot.employee.dao;

import java.util.List;

import com.example.HibernateSpringBoot.employee.dto.Department;
import com.example.HibernateSpringBoot.employee.dto.Employee;
import com.example.HibernateSpringBoot.employee.dto.EmployeeDTO;

public interface EmployeeDao {
	
	public List<Employee> getAllEmployees();
	
	public List<EmployeeDTO> getEmpByDeptName(String departmentName);
	
	public Employee getEmploye(Long empId);
	
	public Department getDepartment(Long deptId);
	
}
