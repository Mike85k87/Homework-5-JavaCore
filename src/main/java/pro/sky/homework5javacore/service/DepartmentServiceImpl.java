package pro.sky.homework5javacore.service;

import org.springframework.stereotype.Service;
import pro.sky.homework5javacore.object.Employee;
import pro.sky.homework5javacore.exception.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @Override
    public Employee maxSalaryEmoloyee(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Employee minSalaryEmployee(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Collection<Employee> getEmployeesInDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAll() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }
    @Override
    public double sumSalaryByDepartment(int departmentId) {
        return employeeService.getAll()
                .stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .mapToDouble(Employee::getSalary)
                .sum();

    }
}