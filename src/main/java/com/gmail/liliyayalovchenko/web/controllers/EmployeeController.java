package com.gmail.liliyayalovchenko.web.controllers;

import com.gmail.liliyayalovchenko.domain.Employee;
import com.gmail.liliyayalovchenko.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String employees(Map<String , Object> model) {
        LOGGER.info("Try to get all employee in controller");
        List<Employee> allEmployees = employeeService.getAllWaiters();
        LOGGER.info("All employees are got in controller");
        model.put("employees", allEmployees);
        return "employees";
    }

}
