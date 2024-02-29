package pro.sky.homework5javacore.service;

import org.springframework.stereotype.Service;
import pro.sky.homework5javacore.exception.EmployeeAlreadyAddedException;
import pro.sky.homework5javacore.exception.EmployeeNotFoundException;
import pro.sky.homework5javacore.exception.EmployeeStorageIsFullException;
import pro.sky.homework5javacore.object.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    private final static int EMPLOYEE_SIZE = 2;
    private String makeKey(String firstName, String lastName) {
        return (firstName + " " + lastName);
    }
    @Override
    public Employee addEmployee(String firstName, String lastName, int departmentId, double salary) throws EmployeeStorageIsFullException {
        if (employees.size() > EMPLOYEE_SIZE) {
            throw new EmployeeStorageIsFullException();}
        Employee employee = new Employee(firstName, lastName, departmentId, salary);
        String key = makeKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Уже есть такой сотрудник в фирме");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }



    @Override
    public Employee removeEmployee(String firstName, String lastName, int departmentId, double salary) {
        String key = makeKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, int departmentId, double salary) {
        String key = makeKey(firstName, lastName);
        if (!employees.containsKey(key)) {
    throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    @Override
    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}