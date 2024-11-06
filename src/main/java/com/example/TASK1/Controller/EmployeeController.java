package com.example.TASK1.Controller;

import com.example.TASK1.Employee;
import com.example.TASK1.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Landing page with form
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Handle form submission to save employee
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/displayAll";
    }

    // Display all employees
    @GetMapping("/displayAll")
    public String displayAll(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employeeList";
    }

    // Display specific employee by ID
    @GetMapping("/display/{employeeId}")
    public String displayById(@PathVariable String employeeId, Model model) {
        Optional<Employee> employee = employeeService.getEmployeeById(employeeId);
        employee.ifPresent(e -> model.addAttribute("employee", e));
        return "employeeDetails";
    }
}
