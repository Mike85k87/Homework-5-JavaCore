package pro.sky.homework5javacore.service;

import pro.sky.homework5javacore.object.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    List<Employee> getAll();
}
