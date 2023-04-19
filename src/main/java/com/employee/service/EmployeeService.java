package com.employee.service;

import java.util.List;

import com.employee.bean.EmployeeBean;

public interface EmployeeService {
	public List<EmployeeBean> getAllEmployee();

	public EmployeeBean getEmployeeById(Long empId);

	public EmployeeBean addEmployee(EmployeeBean employeeBean);

	public EmployeeBean updateEmployee(EmployeeBean employeeBean);

	public void deleteEmployeeById(Long empId);
}
