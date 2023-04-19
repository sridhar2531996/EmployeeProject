package com.employee.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.bean.EmployeeBean;
import com.employee.entity.EmployeeEntity;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	@Autowired
	private EmployeeRepository employeeRepository;
	
// using constructor of the class
//	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//	super();
//	this.employeeRepository = employeeRepository;
//}

	@Override
	public List<EmployeeBean> getAllEmployee() {
		logger.info("Start of EmployeeService getAllEmployee method");
		List<EmployeeBean> employeeBeanList = null;
		try {
			List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
			employeeBeanList = convertEntityToBean(employeeBeanList, employeeEntityList);
		}catch(Exception e) {
			logger.error("Error : "+e.getMessage());
		}
		
		logger.info("End of EmployeeService getAllEmployee method and return employeeBeanList = "+employeeBeanList);
		return employeeBeanList;
	}

	private List<EmployeeBean> convertEntityToBean(List<EmployeeBean> employeeBeanList,
			List<EmployeeEntity> employeeEntityList) {
		if (employeeEntityList != null && employeeEntityList.size() > 0) {
			employeeBeanList= new ArrayList<>();
			for (EmployeeEntity employeeEntity : employeeEntityList) {
				
				EmployeeBean employeeBean = new EmployeeBean();
				
				employeeBean.setEmpId(employeeEntity.getEmpId());
				employeeBean.setEmpName(employeeEntity.getEmpName());
				employeeBean.setEmpAge(employeeEntity.getEmpAge());
				employeeBean.setEmpSalary(employeeEntity.getEmpSalary());
				
				employeeBeanList.add(employeeBean);
			}
		}
		return employeeBeanList;
	}

	@Override
	public EmployeeBean getEmployeeById(Long empId) {
		logger.info("Start of EmployeeService getEmployeeById method with empId = "+empId);
		EmployeeBean employeeBean = null;
		try {
			Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(empId);
			if (employeeOptional.isPresent() && employeeOptional.get() != null) {
				EmployeeEntity employeeEntity = employeeOptional.get();
				employeeBean = new EmployeeBean();
				employeeBean.setEmpId(employeeEntity.getEmpId());
				employeeBean.setEmpName(employeeEntity.getEmpName());
				employeeBean.setEmpAge(employeeEntity.getEmpAge());
				employeeBean.setEmpSalary(employeeEntity.getEmpSalary());
			}
		}catch(Exception e) {
			logger.error("Error = "+e.getMessage());
		}
		
		logger.info("End of EmployeeService getEmployeeById method and return employeeBean = "+employeeBean);
		return employeeBean;
	}

	@Override
	public EmployeeBean addEmployee(EmployeeBean employeeBean) {
		logger.info("Start of EmployeeService addEmployee method with employeeBean = "+employeeBean);
		EmployeeEntity employeeEntity = new EmployeeEntity();
		try {
			employeeEntity.setEmpName(employeeBean.getEmpName());
			employeeEntity.setEmpAge(employeeBean.getEmpAge());
			employeeEntity.setEmpSalary(employeeBean.getEmpSalary());
			employeeEntity = employeeRepository.save(employeeEntity);
			// After saving employee data, data will return employeeEntity
			// Then empentity id set to empbean id
			employeeBean.setEmpId(employeeEntity.getEmpId());
		}catch(Exception e) {
			logger.error("Error : "+e.getMessage());
		}
		logger.info("End of EmployeeService addEmployee method and return employeeBean = "+employeeBean);
		return employeeBean;
	}

	@Override
	public EmployeeBean updateEmployee(EmployeeBean employeeBean) {
		logger.info("Start of EmployeeService updateEmployee method with employeeBean = "+employeeBean);
		EmployeeEntity employeeEntity = new EmployeeEntity();
		try {
			employeeEntity.setEmpId(employeeBean.getEmpId());
			employeeEntity.setEmpName(employeeBean.getEmpName());
			employeeEntity.setEmpAge(employeeBean.getEmpAge());
			employeeEntity.setEmpSalary(employeeBean.getEmpSalary());
			employeeEntity = employeeRepository.save(employeeEntity);
		}catch(Exception e) {
			logger.error("Error : "+e.getMessage());
		}
		
		logger.info("End of EmployeeService updateEmployee method and return employeeBean = "+employeeBean);
		return employeeBean;
	}

	@Override
	public void deleteEmployeeById(Long empId) {
		logger.info("Start of EmployeeService deleteEmployeeById with empId= "+empId);
		try {
			employeeRepository.deleteById(empId);
		}catch(Exception e) {
			logger.error("Error : "+e.getMessage());
		}
		logger.info("End of EmployeeService deleteEmployeeById");
		
	}

}
