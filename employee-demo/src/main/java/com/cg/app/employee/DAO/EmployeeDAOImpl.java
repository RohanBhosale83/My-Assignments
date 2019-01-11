package com.cg.app.employee.DAO;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cg.app.employee.SQL.SQLQueries;
import com.cg.employee.Employee;


@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	JdbcTemplate template;

	private SQLQueries sQueries ;
	@Override
	public void addNewEmployee(Employee employee) {
		template.update(SQLQueries.getInstance().addNewEmployee(),
				new Object[] {employee.getEmpId(),employee.getEmpName(),employee.getSalary()});
		
	}

}
