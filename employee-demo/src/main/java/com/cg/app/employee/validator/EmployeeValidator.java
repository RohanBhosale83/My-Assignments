package com.cg.app.employee.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cg.employee.Employee;

@Component
public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Employee employee= (Employee)target;
		if(employee.getEmpName().length()<2) {
			errors.rejectValue("empName", "empName", "Employee name must contain atleast 2 characters");
		}
		if(employee.getSalary()<100000) {
			errors.rejectValue("salary", "salary", "Salary must be higher than 100000");
		}
		
	}

}
