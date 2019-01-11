package com.cg.app.employee.SQL;

import java.util.ResourceBundle;

public final class SQLQueries {

	private static SQLQueries SQlQuery = new SQLQueries();
	private SQLQueries() {
	}
	
	public static SQLQueries getInstance() {
		return SQlQuery;
	}
	public String addNewEmployee() {
		ResourceBundle bundle = ResourceBundle.getBundle("sql_queries");
		return bundle.getString("EMPLOYEE_INSERT");
	}
}
