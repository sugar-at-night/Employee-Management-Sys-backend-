package com.fiora.crud.service.impl;

import com.fiora.crud.exception.ResourceNotFoundException;
import com.fiora.crud.model.Employee;
import com.fiora.crud.repository.EmployeeRepository;
import com.fiora.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
//       Optional<Employee> employee = employeeRepository.findById(id);
//       if(employee.isPresent()){
//           return employee.get();
//       }else{
//           throw new ResourceNotFoundException("employee","id",id);
//       }
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee", "id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee", "id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        employeeRepository.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee", "id", id));

        employeeRepository.deleteById(id);
    }
}
