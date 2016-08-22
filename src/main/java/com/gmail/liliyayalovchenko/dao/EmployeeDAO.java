package com.gmail.liliyayalovchenko.dao;

import com.gmail.liliyayalovchenko.domain.Employee;

import java.util.List;

public interface EmployeeDAO {

    void save(Employee employee);

    Employee getById(int id);

    List<Employee> findAll();

    Employee findByName(String firstName, String secondName);

    void removeEmployee(String firstName, String secondName);

    List<Employee> getAllWaiters();

    List<Employee> getByFirstName(String employeeName);

    List<Employee> getBySecondName(String employeeSecondName);

//    List<Employee> getEmployeesShortList();
}
