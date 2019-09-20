package com.full.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.full.model.Employee;
import com.full.service.EmployeeService;
import com.full.service.EmployeeServiceImpl;

@Controller
@Configuration
@ComponentScan("com.full.service")
public class TestController {
	

    private static final Logger logger = Logger
            .getLogger(TestController.class);
    
    @Autowired
    private EmployeeService employeeService;
    
 
    public TestController() {
        System.out.println("TestController()");
    }
	
	@RequestMapping("/hai")
	 public String sayHello(){
		 return "hello spring!!!!";
	 }
	
	@RequestMapping("/hello")
	public ModelAndView passParametersWithModelAndView() {
	    ModelAndView modelAndView = new ModelAndView("welcome");
	    modelAndView.addObject("message", "hello theeran from controller");
	    return modelAndView;
	}
	
	@RequestMapping("/getObjects")
	@ResponseBody
	public Map<String,Object> getObjects()
	{
		List<String> list=Arrays.asList("cotacts","leads","invoices","cotacts","leads","invoices");
		Map<String,Object> resultMap=new HashMap<>();
		resultMap.put("status", true);
		resultMap.put("values", list);
		return resultMap;
	}
	
	 
	    @RequestMapping(value = "/")
	    public ModelAndView listEmployee(ModelAndView model) throws IOException {
	        List<Employee> listEmployee = employeeService.getAllEmployees();
	        model.addObject("listEmployee", listEmployee);
	        model.setViewName("home");
	        return model;
	    }
	 
	    @RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
	    public ModelAndView newContact(ModelAndView model) {
	        Employee employee = new Employee();
	        model.addObject("employee", employee);
	        model.setViewName("EmployeeForm");
	        return model;
	    }
	 
	    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	    public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
	        if (employee.getId() == 0) { // if employee id is 0 then creating the
	            // employee other updating the employee
	            employeeService.addEmployee(employee);
	        } else {
	            employeeService.updateEmployee(employee);
	        }
	        return new ModelAndView("redirect:/");
	    }
	 
	    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	    public ModelAndView deleteEmployee(HttpServletRequest request) {
	        int employeeId = Integer.parseInt(request.getParameter("id"));
	        employeeService.deleteEmployee(employeeId);
	        return new ModelAndView("redirect:/");
	    }
	 
	    @RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	    public ModelAndView editContact(HttpServletRequest request) {
	        int employeeId = Integer.parseInt(request.getParameter("id"));
	        Employee employee = employeeService.getEmployee(employeeId);
	        ModelAndView model = new ModelAndView("EmployeeForm");
	        model.addObject("employee", employee);
	 
	        return model;
	    }
   }
