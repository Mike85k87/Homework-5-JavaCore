package pro.sky.homework5javacore.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.homework5javacore.object.Employee;
import pro.sky.homework5javacore.service.EmployeeService;
import pro.sky.homework5javacore.util.EmployeeNameValidator;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")

    public Employee addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                @RequestParam("departmentId") int departmentId, @RequestParam("salary") double salary) {
        EmployeeNameValidator.validateIsAlpha(firstName, lastName);
        return employeeService.addEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                   @RequestParam("departmentId") int departmentId, @RequestParam("salary") double salary) {
        EmployeeNameValidator.validateIsAlpha(firstName, lastName);
        return employeeService.removeEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                 @RequestParam("departmentId") int departmentId, @RequestParam("salary") double salary) {
        EmployeeNameValidator.validateIsAlpha(firstName, lastName);
        return employeeService.findEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping()
    public Collection<Employee> getAll() {
        return employeeService.getAll();
    }
}
