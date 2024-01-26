package pro.sky.homework5javacore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homework5javacore.object.Employee;
import pro.sky.homework5javacore.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")

public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAll() {

        return departmentService.getAll();
    }

    @GetMapping(value = "/all", params = "departmentId")
    public Collection<Employee> getAllDepartment(@RequestParam int departmentId) {
        return departmentService.getEmployeesInDepartment(departmentId);
    }

    @GetMapping(value = "/min-salary", params = "departmentId")
    public Employee minsalary(@RequestParam int departmentId) {
        return departmentService.minSalaryEmployee(departmentId);
    }

    @GetMapping(value = "/max-salary", params = "departmentId")
    public Employee maxsalary(@RequestParam int departmentId) {
        return departmentService.maxSalaryEmoloyee(departmentId);
    }
}
