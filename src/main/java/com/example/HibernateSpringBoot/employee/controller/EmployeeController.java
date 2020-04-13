package com.example.HibernateSpringBoot.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.HibernateSpringBoot.employee.dto.Employee;
import com.example.HibernateSpringBoot.employee.dto.EmployeeDTO;
import com.example.HibernateSpringBoot.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService; 	 		
	
	@GetMapping(value="/getAllEmployees", produces={"application/json", "application/xml"})
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping(value="/getEmpByDepartment")
	public List<EmployeeDTO> getEmpByDepartment(@RequestParam("departmentName") String departmentName){
		return employeeService.getEmpByDeptName(departmentName);
	}
	
	
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public String handleHttpMediaTypeNotAcceptableException() {
	    return "acceptable MIME type:" + MediaType.APPLICATION_XML;
	}
	
}
