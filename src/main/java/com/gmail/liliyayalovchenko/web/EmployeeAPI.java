package com.gmail.liliyayalovchenko.web;

import com.gmail.liliyayalovchenko.domain.Employee;
import com.gmail.liliyayalovchenko.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeAPI {

    private EmployeeService employeeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeAPI.class);

    /**Method that gets a list of all employees
     * (only the first names and second names)**/
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public List<Employee> employee() {
        LOGGER.info("Try to get all employee in controller");
        List<Employee> allEmployees = employeeService.getAllEmployeesShortList();
        LOGGER.info("All employees are got in controller");
        return allEmployees;
    }

    /**Method that gets the employee by id**/
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Employee employee(@PathVariable long id) {
        LOGGER.info("Try to get all employee in controller");
        Employee employeeById = employeeService.getEmployeeById(id);
        LOGGER.info("All employees are got in controller");
        return employeeById;
    }

    /**Method that gets the employee
     * by first name and second name**/
    @RequestMapping(value = "/employee/{employeeName}/{employeeSecondName}", method = RequestMethod.GET)
    public Employee employee(@PathVariable String employeeName,
                                   @PathVariable String employeeSecondName) {
        LOGGER.info("Try to get all employee in controller");
        Employee employee = employeeService.getEmployeeByName(employeeName, employeeSecondName);
        LOGGER.info("All employees are got in controller");
        return employee;
    }

    /**Method that gets the employees list
     * by first name**/
    @RequestMapping(value = "/employee/{employeeName}", method = RequestMethod.GET)
    public List<Employee> employeeByFirstName(@PathVariable String employeeName) {
        LOGGER.info("Try to get all employee by name " + employeeName + " in controller");
        List<Employee> employeesByName = employeeService.getEmployeeByFirstName(employeeName);
        LOGGER.info("All employees by FirstName are got in controller");
        return employeesByName;
    }

    /**Method that gets the employees list
     * by second name**/
    @RequestMapping(value = "/employee/{employeeSecondName}", method = RequestMethod.GET)
    public List<Employee> employeeBySecondName(@PathVariable String employeeSecondName) {
        LOGGER.info("Try to get all employee in controller");
        List<Employee> employeeBySecondName = employeeService.getEmployeeBySecondName(employeeSecondName);
        LOGGER.info("All employees are got in controller");
        return employeeBySecondName;
    }


    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
