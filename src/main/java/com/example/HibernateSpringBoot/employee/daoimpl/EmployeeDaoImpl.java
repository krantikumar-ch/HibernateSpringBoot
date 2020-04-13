package com.example.HibernateSpringBoot.employee.daoimpl;

import java.util.List;

import org.hibernate.query.NativeQuery ;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.example.HibernateSpringBoot.config.db.DaoSupport;
import com.example.HibernateSpringBoot.employee.dao.EmployeeDao;
import com.example.HibernateSpringBoot.employee.dto.Employee;
import com.example.HibernateSpringBoot.employee.dto.EmployeeDTO;
import com.example.HibernateSpringBoot.utils.SQLQueryUtils;

@Repository
public class EmployeeDaoImpl extends DaoSupport implements EmployeeDao {

	
	@Override
	@SuppressWarnings(value = "unchecked")
	public List<Employee> getAllEmployees() {
		return getHibernateTemplate().
				execute(session -> session.createQuery("from Employee").list());
	}

	@SuppressWarnings({ "unchecked", "deprecation","rawtypes", "deprecated" })
	@Override
	public List<EmployeeDTO> getEmpByDeptName(String departmentName) {
		
		return (List<EmployeeDTO>) getHibernateTemplate().execute(session ->{
			String query=SQLQueryUtils.getQuery("EMPLOYEES.GET_EMP_BY_DEPT_NAME");
			NativeQuery nQuery = session.createNativeQuery(query);
			nQuery.setParameter("departmentName", departmentName);
			
			nQuery.addScalar("employeeId", StandardBasicTypes.LONG);
			nQuery.addScalar("firstName", StandardBasicTypes.STRING);
			nQuery.addScalar("lastName", StandardBasicTypes.STRING);
			nQuery.addScalar("email", StandardBasicTypes.STRING);
			nQuery.addScalar("departmentName", StandardBasicTypes.STRING);
			nQuery.addScalar("departmentId", StandardBasicTypes.LONG);
			nQuery.addScalar("salary", StandardBasicTypes.FLOAT);
			
			
			//There is no replacement for ResultTransformer in Hibernate 5.3, therefore as recommended here, for the moment it can be used as-is.
			//http://wiki.openbravo.com/wiki/Hibernate_5.3_Migration_Guide
			nQuery.setResultTransformer(Transformers.aliasToBean(EmployeeDTO.class));
			
			return nQuery.getResultList();
		});
		
	}
	
	

}
