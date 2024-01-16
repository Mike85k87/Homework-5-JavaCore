package pro.sky.homework5javacore.service;

import org.springframework.stereotype.Service;
import pro.sky.homework5javacore.object.Employee;
import pro.sky.homework5javacore.exception.EmployeeAlreadyAddedException;
import pro.sky.homework5javacore.exception.EmployeeStorageIsFullException;
import pro.sky.homework5javacore.exception.EmployeeNotFoundException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;
    public EmployeeServiceImpl(){
        this.employees=new HashMap<>();
    }

    private static final int EMPLOYEE_SIZE = 5;

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() == EMPLOYEE_SIZE) {
            throw new EmployeeStorageIsFullException();
        }

        Employee employee = new Employee (firstName, lastName);

        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }
    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);


        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(employee.getFullName());
    }
    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(employee.getFullName());
    }
    @Override
    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}