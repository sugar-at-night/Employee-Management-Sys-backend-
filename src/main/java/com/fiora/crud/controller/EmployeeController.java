package com.fiora.crud.controller;

import com.fiora.crud.model.Employee;
import com.fiora.crud.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    //add
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee (@RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    //get all
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //get by ID
    //http://localhost:57724/api/employees/{id}
    //http://localhost:57724/api/employees/1
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    //update
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId, @RequestBody Employee employee){
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, employeeId), HttpStatus.OK);
    }

    //delete by ID
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Deleted successfully",HttpStatus.OK);

    }








}
