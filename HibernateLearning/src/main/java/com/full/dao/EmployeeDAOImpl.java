package com.full.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.full.model.Employee;

@Repository
public class EmployeeDAOImpl  implements EmployeeDao
{
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addEmployee(Employee employee) {
		 sessionFactory.getCurrentSession().saveOrUpdate(employee);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployees() {
		return sessionFactory.getCurrentSession().createQuery("from Employee")
                .list();
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		 Employee employee = (Employee) sessionFactory.getCurrentSession().load(
	                Employee.class, employeeId);
	        if (null != employee) {
	            this.sessionFactory.getCurrentSession().delete(employee);
	        }
		
	}

	 public Employee getEmployee(int empid) {
	        return (Employee) sessionFactory.getCurrentSession().get(
	                Employee.class, empid);
	 }
	 
	 @Override
	 public Employee updateEmployee(Employee employee) {
	        sessionFactory.getCurrentSession().update(employee);
	        return employee;
	 }

}
