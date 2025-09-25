package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custome_exceptions.ResourceNotFoundException;
import com.app.dao.EmployeeRepository;
import com.app.entities.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {
	//dep : dao i/f
	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public List<Employee> getAllEmpDetails() {
			System.out.println("emp repo "+empRepo.getClass());
			//to chk lombok
			//System.out.println(new Employee().getName());
			return empRepo.findAll();
	}

	@Override
	public Employee addEmp(Employee transientEmp) {
		
		return empRepo.save(transientEmp);
	}//returns detached emp instance to the caller

	@Override
	public String deleteEmp(long empId) {
		empRepo.deleteById(empId);
		return "Emp details deled for emp id "+empId;
	}

	@Override
	public Employee getEmpDetails(long empId) {
		 //throw dummy exc : to test global exc handler
		//throw new RuntimeException("some dummy exc!!!!");
		return empRepo.findById(empId).
				orElseThrow(()->new ResourceNotFoundException("Invalid emp ID " + empId));
	}

	@Override
	public Employee updateEmpDetails(Employee detachedEmp) {
		if(empRepo.existsById(detachedEmp.getId()))
			return empRepo.save(detachedEmp);
		throw new ResourceNotFoundException("Invalid ID Failed !!!!"+detachedEmp.getId());	
	}

}
