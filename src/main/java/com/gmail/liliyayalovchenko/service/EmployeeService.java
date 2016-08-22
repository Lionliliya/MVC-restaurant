package com.gmail.liliyayalovchenko.service;


import com.gmail.liliyayalovchenko.dao.EmployeeDAO;
import com.gmail.liliyayalovchenko.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private EmployeeDAO employeeDAO;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Transactional
    public List<Employee> getAllEmployees() {
        LOGGER.info("Try to get all employees");
        List<Employee> employees = employeeDAO.findAll();
        LOGGER.info("All employees are got.");
        return employees;
    }

    @Transactional
    public Employee getEmployeeByName(String name, String surname) {
        LOGGER.info("Try to get all employees");
        Employee employee = employeeDAO.findByName(name, surname);
        LOGGER.info("Employee is got: id = " + employee.getId());
        return employee;
    }

    @Transactional
    public List<Employee> getAllWaiters() {
        LOGGER.info("Try to get all waiters");
        List<Employee> waiters = employeeDAO.getAllWaiters();
        LOGGER.info("Waiters are got");
        return waiters;
    }

    @Transactional
    public Employee getEmployeeById(long id) {
        LOGGER.info("Try to get employee by id");
        Employee employee = employeeDAO.getById(id);
        LOGGER.info("Employee by id " + id +" are got");
        return employee;
    }

    @Transactional
    public List<Employee> getEmployeeByFirstName(String employeeName) {
        LOGGER.info("Try to get employee by first name");
        List<Employee> employees = employeeDAO.getByFirstName(employeeName);
        LOGGER.info("Employee by name " + employeeName +" are got");
        return employees;
    }

    @Transactional
    public List<Employee> getEmployeeBySecondName(String employeeSecondName) {
        LOGGER.info("Try to get employee by second name");
        List<Employee> employees = employeeDAO.getBySecondName(employeeSecondName);
        LOGGER.info("Employee by name " + employeeSecondName +" are got");
        return employees;
    }

    @Transactional
    public List<Employee> getAllEmployeesShortList() {
        LOGGER.info("Try to get all employees only names and second names");
        List<Employee> employees = employeeDAO.findAll();
        LOGGER.info("All employees are got.");
        List<Employee> resultList = new ArrayList<>();
        for (Employee employee : employees) {
            Employee employeeShort = new Employee(employee.getSecondName(), employee.getFirstName());
            resultList.add(employeeShort);
        }
        return resultList;
    }

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }



}
