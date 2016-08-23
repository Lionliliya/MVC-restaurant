package com.gmail.liliyayalovchenko.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.gmail.liliyayalovchenko.domain.Employee;
import com.gmail.liliyayalovchenko.service.EmployeeService;
import com.gmail.liliyayalovchenko.web.JSON_View.Views;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeAPI {

    private EmployeeService employeeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAPI.class);


    /**
     * Method that gets the employee by id
     * **/
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Employee employeeById(@PathVariable int id) {
        LOGGER.info("Try to get employee by id");
        Employee employeeById = employeeService.getEmployeeById(id);
        LOGGER.info("Employee by id is got");
        return employeeById;
    }

    /**
     * Method that gets the employee
     * by first name and second name
     * **/
    @RequestMapping(value = "/employee/{employeeName}/{employeeSecondName}", method = RequestMethod.GET)
    public Employee employeeByFullName(@PathVariable String employeeName,
                                       @PathVariable String employeeSecondName) {
        LOGGER.info("Try to get all employee in controller");
        Employee employee = employeeService.getEmployeeByName(employeeName, employeeSecondName);
        LOGGER.info("All employees are got in controller");
        return employee;
    }

    /**
     * Method that gets the employees list
     * by first name
     * **/
    @RequestMapping(value = "/employee/firstName/{employeeName}", method = RequestMethod.GET)
    public List<Employee> employeeByFirstName(@PathVariable String employeeName) {
        LOGGER.info("Try to get all employee by name " + employeeName + " in controller");
        List<Employee> employeesByName = employeeService.getEmployeeByFirstName(employeeName);
        LOGGER.info("All employees by FirstName are got in controller");
        return employeesByName;
    }

    /**
     * Method that gets the employees list
     * by second name
     * **/
    @RequestMapping(value = "/employee/secondName/{employeeSecondName}", method = RequestMethod.GET)
    public List<Employee> employeeBySecondName(@PathVariable String employeeSecondName) {
        LOGGER.info("Try to get all employee in controller");
        List<Employee> employeeBySecondName = employeeService.getEmployeeBySecondName(employeeSecondName);
        LOGGER.info("All employees are got in controller");
        return employeeBySecondName;
    }


    /**
     * Method that gets a list of all employees
     * (only the first names and second names)
     * **/
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    @JsonView(Views.Public.class)
    public ResponseEntity<List<Employee>> listAllUsers() {
        List<Employee> employees = employeeService.getAllEmployees();
        if(employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
