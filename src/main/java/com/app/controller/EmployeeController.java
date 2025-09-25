package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.entities.Employee;
import com.app.service.IEmployeeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class EmployeeController {
	//dep : service layer i/f
	@Autowired
	private IEmployeeService empService;
	
public EmployeeController() {
	System.out.println("in ctor of "+getClass());
}

//add REST API endpoint to ret list of all emps
@GetMapping
public List<Employee> listEmployees(){
	System.out.println("in list emps ");
	return empService.getAllEmpDetails();
}
//add REST API endpoint to add emp details (create new resource)
@PostMapping
public ResponseEntity<?> addEmpDetails(@RequestBody @Valid Employee transientEmp) {
	System.out.println("in add dtls "+transientEmp);
	try {
	//invoke service layer method
	return new  ResponseEntity<>(empService.addEmp(transientEmp), HttpStatus.CREATED);
	}catch(RuntimeException e) {
		System.out.println("err in add emp "+e);
		return new  ResponseEntity<>(new ApiResponse(e.getMessage()),HttpStatus.BAD_REQUEST);//=>invalid data from client
	}
}
//add REST API endpoint to delete emp details
@DeleteMapping("/{empId}")
public ResponseEntity<?> deleteEmpDetails(@PathVariable long empId){
	System.out.println("in del emp "+empId);
	try {
	return ResponseEntity.ok(new ApiResponse(empService.deleteEmp(empId)));
	}catch(RuntimeException e) {
		System.out.println("err in del emp "+e);
		return new  ResponseEntity<>(new ApiResponse("Invalid Emp ID !!!"),HttpStatus.NOT_FOUND);//=>invalid emp_id
	}
	}
//add REST API endpoint to
@GetMapping("/{empId}")
public ResponseEntity<?> getEmpDetails(@PathVariable @Valid @Min(1) @Max(1000) long empId){
	System.out.println("in get emp "+empId);
	//try {
	return ResponseEntity.ok((empService.getEmpDetails(empId)));
//	}catch(RuntimeException e) {
//		System.out.println("err in get emp "+e);
//		return new  ResponseEntity<>(new ApiResponse(e.getMessage()),HttpStatus.NOT_FOUND);//=>invalid emp_id
//	}
	}
	//add REST API endpoint to update existing emp details
@PutMapping
public ResponseEntity<?> updateEmpDeatils(@RequestBody Employee detachedEmp) {
	try {
	System.out.println("in update emp "+detachedEmp);
	return ResponseEntity.ok(empService.updateEmpDetails(detachedEmp));
	}catch(RuntimeException e) {
		System.out.println("err in update emp "+e);
		return new  ResponseEntity<>(new ApiResponse(e.getMessage()),HttpStatus.NOT_FOUND);//=>invalid emp_id
	}
}
}
