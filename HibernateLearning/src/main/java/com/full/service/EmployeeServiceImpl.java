package com.full.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.full.dao.EmployeeDAOImpl;
import com.full.dao.EmployeeDao;
import com.full.model.Employee;
 

@Service
@Transactional
@ComponentScan("com.full.dao")
public class EmployeeServiceImpl implements EmployeeService {
 
    @Autowired
    private EmployeeDao employeeDAO;
 
    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }
 
    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
 
    @Override
    @Transactional
    public void deleteEmployee(Integer employeeId) {
        employeeDAO.deleteEmployee(employeeId);
    }
 
    public Employee getEmployee(int empid) {
        return employeeDAO.getEmployee(empid);
    }
 
    public Employee updateEmployee(Employee employee) {
        // TODO Auto-generated method stub
        return employeeDAO.updateEmployee(employee);
    }
 
    public void setEmployeeDAO(EmployeeDao employeeDAO) {
     //   this.employeeDAO = employeeDAO;
    }
}
