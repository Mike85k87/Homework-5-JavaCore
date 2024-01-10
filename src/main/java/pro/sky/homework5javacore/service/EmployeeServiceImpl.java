package pro.sky.homework5javacore.service;

import org.springframework.stereotype.Service;
import pro.sky.homework5javacore.object.Employee;
import pro.sky.homework5javacore.exception.EmployeeAlreadyAddedException;
import pro.sky.homework5javacore.exception.EmployeeStorageIsFullException;
import pro.sky.homework5javacore.exception.EmployeeNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final     List<Employee> employees = new ArrayList<>(List.of(

            new Employee("Ivan", "Ivanov"),

            new Employee("Petr", "Petrov"),

            new Employee("Maksim", "Sidorov"),

            new Employee("Mikhail", "Mkhailov")));

    private static final int EMPLOYEE_SIZE = 5;

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() == EMPLOYEE_SIZE) {
            throw new EmployeeStorageIsFullException();
        }

        Employee employee = new Employee(firstName, lastName);

        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }
    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);


        if (!employees.remove(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }
    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }
    @Override
    public List<Employee> getAll() {
        return employees;
    }
}