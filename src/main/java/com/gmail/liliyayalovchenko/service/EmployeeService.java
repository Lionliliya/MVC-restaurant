package com.gmail.liliyayalovchenko.service;


import com.gmail.liliyayalovchenko.dao.EmployeeDAO;
import com.gmail.liliyayalovchenko.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }


}
