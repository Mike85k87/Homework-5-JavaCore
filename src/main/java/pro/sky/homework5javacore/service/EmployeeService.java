package pro.sky.homework5javacore.service;

import pro.sky.homework5javacore.exception.EmployeeStorageIsFullException;
import pro.sky.homework5javacore.object.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int departmentId, double salary) throws EmployeeStorageIsFullException;

    Employee removeEmployee(String firstName, String lastName, int departmentId, double salary);

    Employee findEmployee(String firstName, String lastName, int departmentId, double salary);

    Collection<Employee> getAll();
}
