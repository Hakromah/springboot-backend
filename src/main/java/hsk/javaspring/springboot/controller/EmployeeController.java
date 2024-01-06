package hsk.javaspring.springboot.controller;

import hsk.javaspring.springboot.exception.ResourceNotFoundException;
import hsk.javaspring.springboot.model.Employee;
import hsk.javaspring.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // GET ALL created employees
    @GetMapping
    public List<Employee> allEmployees() {
        return employeeRepository.findAll();
    }

    // CREATE Employees and save to database
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // GET Employee by ID
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        return ResponseEntity.ok(employee);
    }

    // UPDATE employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employeeDetails) {
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        //Setting the employee details with new values
        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());
        updateEmployee.setEmailId(employeeDetails.getEmailId());

        // save the updated values to the employee database
        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    // DELETE employee by ID
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not exist with id : " + id));
        employeeRepository.delete(employee);
        System.out.println("deleted successfully!!");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
