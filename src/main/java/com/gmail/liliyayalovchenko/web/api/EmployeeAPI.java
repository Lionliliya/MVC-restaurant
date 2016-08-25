package com.gmail.liliyayalovchenko.web.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    private EmployeeService employeeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAPI.class);

    @Autowired
    private ObjectMapper objectMapper;


    /**
     * Method that gets the employee by id
     * If employee is a waiter or waitress, he's orders also serialized
     *
     * @author Liliya Yalovchenko
     * **/
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> employeeById(@PathVariable int id) {
        LOGGER.info("Try to get employee by id");
        Employee employeeById = employeeService.getEmployeeById(id);
        objectMapper.setConfig(objectMapper.getSerializationConfig().withView(Views.Internal.class));
        objectMapper.setVisibility(objectMapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.NONE));
        LOGGER.info("Employee by id is got");
        if(employeeById == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeById, HttpStatus.OK);
    }

    /**
     * Method that gets the employee
     * by first name and second name
     * If employee is a waiter or waitress, he's orders also serialized
     *
     * @author Liliya Yalovchenko
     * **/
    @RequestMapping(value = "/employee/{employeeName}/{employeeSecondName}", method = RequestMethod.GET)
    public ResponseEntity<Employee> employeeByFullName(@PathVariable String employeeName,
                                       @PathVariable String employeeSecondName) {
        LOGGER.info("Try to get all employee in controller");
        Employee employee = employeeService.getEmployeeByName(employeeName, employeeSecondName);
        LOGGER.info("All employees are got in controller");
        objectMapper.setConfig(objectMapper.getSerializationConfig().withView(Views.Internal.class));
        objectMapper.setVisibility(objectMapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.NONE));
        if(employee == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /**
     * Method that gets the employees list
     * by first name
     * If employee is a waiter or waitress, he's orders also serialized
     *
     * @author Liliya Yalovchenko
     * **/
    @RequestMapping(value = "/employee/firstName/{employeeName}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> employeeByFirstName(@PathVariable String employeeName) {
        LOGGER.info("Try to get all employee by name " + employeeName + " in controller");
        List<Employee> employeesByName = employeeService.getEmployeeByFirstName(employeeName);
        LOGGER.info("All employees by FirstName are got in controller");
        objectMapper.setConfig(objectMapper.getSerializationConfig().withView(Views.Internal.class));
        objectMapper.setVisibility(objectMapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.NONE));
        if(employeesByName.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeesByName, HttpStatus.OK);
    }

    /**
     * Method that gets the employees list
     * by second name
     * If employee is a waiter or waitress, he's orders also serialized
     *
     * @author Liliya Yalovchenko
     * **/
    @RequestMapping(value = "/employee/secondName/{employeeSecondName}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> employeeBySecondName(@PathVariable String employeeSecondName) {
        LOGGER.info("Try to get all employee in controller");
        List<Employee> employeeBySecondName = employeeService.getEmployeeBySecondName(employeeSecondName);
        LOGGER.info("All employees are got in controller");
        objectMapper.setConfig(objectMapper.getSerializationConfig().withView(Views.Internal.class));
        objectMapper.setVisibility(objectMapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.NONE));
        if(employeeBySecondName.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employeeBySecondName, HttpStatus.OK);
    }


    /**
     * Method that gets a list of all employees
     * (only the first names and second names)
     *
     * @author Liliya Yalovchenko
     * **/
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listAllUsers() {
        List<Employee> employees = employeeService.getAllEmployees();
        objectMapper.setConfig(objectMapper.getSerializationConfig().withView(Views.Public.class));
        if(employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}
