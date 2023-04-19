package com.employee.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.bean.EmployeeBean;
import com.employee.service.EmployeeService;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@Controller
public class EmployeeRestController {
	private final Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
//	@GetMapping(value = "/getallemployee")
	@RequestMapping(value = "/getallemployee",method =RequestMethod.GET ,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
//	public <EmployeeBean> getAllEmployee() {
	public ResponseEntity<?>getAllEmployee() {
		logger.info("Start of Rest controller getAllEmployee method ");
		List<EmployeeBean> employeeBeanList = null;
		try {
			logger.info("Calling of employeeService.getAllEmployee method");
			employeeBeanList = employeeService.getAllEmployee();
			logger.info("Size of employeeBeanList=" + employeeBeanList.size());
		} catch (Exception e) {
			logger.error("Error Message " + e.getMessage());
			return new ResponseEntity(employeeBeanList, HttpStatus.NOT_FOUND);
		}
		logger.info("End of Rest controller getAllEmployee method and return employeeBeanList=" + employeeBeanList);
//		return employeeBeanList;
		return new ResponseEntity(employeeBeanList, HttpStatus.OK);
	}

///	@GetMapping(value = "/getemployeebyid/{empId}")
	@RequestMapping(value = "/getemployeebyid/{empId}",method =RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	//public EmployeeBean getEmployeeById(@PathVariable("empId") Long empId) {
	public ResponseEntity<?> getEmployeeById(@PathVariable("empId") Long empId) {
		logger.info("Start of Rest controller getEmployeeById method with id= " + empId);
		EmployeeBean employeeBean = null;
		try {
			logger.info("calling of employeeService.getEmployeeById method");
			employeeBean = employeeService.getEmployeeById(empId);
		} catch (Exception e) {
			logger.error("Error : " + e.getMessage());
		}
		logger.info("End of Rest controller getEmployeeById method and return employeeBean=" + employeeBean);
		//return employeeBean;
		return new ResponseEntity(employeeBean,HttpStatus.OK); 
	}

//	@PostMapping(value = "/addemployee")
	@RequestMapping(value = "/addemployee",method =RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
//	public EmployeeBean addEmployee(@RequestBody EmployeeBean employeeBean) {
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeBean employeeBean) {
		logger.info("Start of Rest controller addEmployee method with employeeBean= " + employeeBean);
		EmployeeBean empBean=null;
		try {
			logger.info("Calling of employeeService.addEmployee method");	
			empBean = employeeService.addEmployee(employeeBean);
		}catch(Exception e) {
			logger.error("Error Massege " + e.getMessage());
		}
		
		logger.info("End of Rest controller addEmployee method and return empBean=" + empBean);
		//return empBean;
		return new ResponseEntity(empBean,HttpStatus.OK);
	}

//	@PutMapping(value = "/updateemployee")
	
	@RequestMapping(value = "/updateemployee",method =RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
//	public EmployeeBean updateEmployee(@RequestBody EmployeeBean employeeBean) {
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeBean employeeBean) {
		logger.info("Start of Rest controller updateEmployee method with employeeBean= " + employeeBean);
		EmployeeBean empBean=null;
		try {
			logger.info("Calling of employeeService.updateEmployee method");
			empBean = employeeService.updateEmployee(employeeBean);
		}catch(Exception e) {
			logger.error("Error Massege " + e.getMessage());
		}
		logger.info("End of Rest controller updateEmployee method and return empBean=" + empBean);
//		return empBean;
		return new ResponseEntity(empBean, HttpStatus.OK);
	}

//	@DeleteMapping(value = "/deleteemployeebyid/{empId}")
	@RequestMapping(value = "/deleteemployeebyid/{empId}",method =RequestMethod.DELETE)
	@ResponseBody
	public void deleteEmployeeById(@PathVariable("empId") Long empId) {
		logger.info("Start of Rest controller deleteEmployeeById method with id= " + empId);
		try {
			logger.info("Calling of employeeService.deleteEmployeeById method");
			employeeService.deleteEmployeeById(empId);
		}catch(Exception e) {
			logger.error("Error Massege " + e.getMessage());
		}
		
		logger.info("End of Rest controller deleteEmployeeById method");
	}
	@GetMapping(value = "/")
	public String login() {
		return ("<h1>Welcome</h1>");
	}
	
}
