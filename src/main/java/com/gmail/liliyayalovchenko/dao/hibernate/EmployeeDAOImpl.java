package com.gmail.liliyayalovchenko.dao.hibernate;

import com.gmail.liliyayalovchenko.dao.EmployeeDAO;
import com.gmail.liliyayalovchenko.domain.Employee;
import com.gmail.liliyayalovchenko.domain.Position;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void save(Employee employee) {
       sessionFactory.getCurrentSession().save(employee);

    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Employee getById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Employee employee = currentSession.load(Employee.class, id);
        if (employee == null) {
            throw new RuntimeException("Cant get Employee by this id. Wrong id!");
        } else {
            return employee;
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Employee> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(Employee.class);
        return criteria.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void removeEmployee(String firstName, String secondName) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.and(Restrictions.eq("firstName", firstName),
                Restrictions.eq("secondName", secondName)));
        Employee employee = (Employee) criteria.list().get(0);
        if (employee != null) {
            session.delete(employee);
        } else {
            throw new RuntimeException("Cant find employee by this name. Error!");
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Employee findByName(String firstName, String secondName) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.and(Restrictions.eq("firstName", firstName),
                Restrictions.eq("secondName", secondName)));
        Employee employee = (Employee) criteria.list().get(0);
        if (employee == null) {
            throw new RuntimeException("Cant get employee by this name!Wrong name!");
        } else {
            return employee;
        }
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Employee> getAllWaiters() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Employee.class);
        criteria.add(Restrictions.or(Restrictions.eq("position", Position.WAITER),
                                     Restrictions.eq("position", Position.WAITRESS)));
        return criteria.list();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Employee> getByFirstName(String employeeName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select  e from Employee e where e.firstName =:employeeName");
        query.setParameter("employeeName", employeeName);
        return query.getResultList();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Employee> getBySecondName(String employeeSecondName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select  e from Employee e where e.secondName =:employeeSecondName");
        query.setParameter("employeeSecondName",employeeSecondName);
        return query.getResultList();
    }

//    @Override
//    @Transactional(propagation = Propagation.MANDATORY)
//    public List<Employee> getEmployeesShortList() {
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("select  e.firstName, e.secondName from Employee e");
//        return null;
//    }
}
