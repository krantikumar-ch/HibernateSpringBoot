package com.example.HibernateSpringBoot.employee.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HibernateSpringBoot.employee.dao.EmployeeDao;
import com.example.HibernateSpringBoot.employee.dto.Employee;
import com.example.HibernateSpringBoot.employee.dto.EmployeeDTO;
import com.example.HibernateSpringBoot.employee.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees();
		
	}

	@Override
	public List<EmployeeDTO> getEmpByDeptName(String departmentName) {
		return employeeDao.getEmpByDeptName(departmentName);
	}

}
