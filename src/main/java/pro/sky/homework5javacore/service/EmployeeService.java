package pro.sky.homework5javacore.service;

import pro.sky.homework5javacore.object.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int departmentId, double salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> getAll();
}
