package com.cg.app.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.app.employee.DAO.EmployeeDAO;
import com.cg.employee.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO dao;
	
	@Override
	public void addNewEmployee(Employee employee) {
		dao.addNewEmployee(employee);
	}
}
