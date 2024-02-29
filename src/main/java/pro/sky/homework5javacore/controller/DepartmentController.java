package pro.sky.homework5javacore.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.homework5javacore.object.Employee;
import pro.sky.homework5javacore.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")

public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAll() {

        return departmentService.getAll();
    }

    @GetMapping("/{id}/employees")
    public Collection<Employee> getAllDepartment(@PathVariable int departmentId) {
        return departmentService.getEmployeesInDepartment(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public Employee minsalary(@PathVariable int departmentId) {
        return departmentService.minSalaryEmployee(departmentId);
    }

    @GetMapping("/{id}/salary/max")
    public Employee maxsalary(@PathVariable int departmentId) {
        return departmentService.maxSalaryEmoloyee(departmentId);
    }
    @GetMapping("/{id}/salary/sum")
    public double sumSalaryByDepartment(@PathVariable int departmentId) {
        return departmentService.sumSalaryByDepartment(departmentId);
    }
}
