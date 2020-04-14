package com.example.HibernateSpringBoot.employee.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department {
	
	@Id @Column(name="department_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long departmentId;
	
	@Column(name="department_name")
	private String departmentName;
	
	@Column(name="location_id")
	private Long locationId;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="department_id")
	private Set<Employee> employees;


	@Override
	public String toString(){
		return " departmentId: "+departmentId+" departmentName "+departmentName+" locationId "+locationId;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	
	
	

}
