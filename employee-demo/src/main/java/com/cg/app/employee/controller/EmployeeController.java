package com.cg.app.employee.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cg.employee.Employee;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@RequestMapping(value="/save", method=RequestMethod.GET)
	public String askDetails(Map map) {
		map.put("employee",new Employee());
		return "input";
	}
	
	/*
	 * @RequestMapping("/save") public String save(@RequestParam("empId")int empId,
	 * 
	 * @RequestParam("empName")String empName,
	 * 
	 * @RequestParam("salary")double salary,Model model) {
	 * 
	 * Employee employee = new Employee(empId,empName,salary);
	 * model.addAttribute("employee", employee);
	 * 
	 * return "display"; }
	 */
	
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@ModelAttribute("employee") Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			return "input";
		}
		return "display";
	}
}
