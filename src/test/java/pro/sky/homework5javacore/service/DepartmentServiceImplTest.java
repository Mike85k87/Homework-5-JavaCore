package pro.sky.homework5javacore.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.homework5javacore.object.Employee;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    DepartmentServiceImpl departmentService;
    private List<Employee> employees = List.of(new Employee("Alex",
                    "Petrov", 1, 30_000),
            new Employee("Alex",
                    "Petrovich", 1, 300_000),
            new Employee("Alexis",
                    "Petrover", 2, 30_000));

    @Test
    void maxSalaryEmployee_shouldReturnEmployeeWithMaxSalaryWhenEmployeesInDepartment() {
        when(employeeService.getAll()).thenReturn(employees);
        Employee result = departmentService.maxSalaryEmoloyee(employees.get(0).getDepartmentId());
        assertEquals(employees.get(1), result);
    }

    @Test
    void maxSalaryEmployee_shouldThrowExceptionNotEmployeeInDepartment() {
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        assertThrows(IllegalArgumentException.class,
                () -> departmentService.maxSalaryEmoloyee(1));
    }

    @Test
    void minSalaryEmployee_shouldReturnEmployeeWithMinSalaryWhenEmployeeInDepartment() {
        when(employeeService.getAll()).thenReturn(employees);
        Employee result = departmentService.minSalaryEmployee(employees.get(1).getDepartmentId());
        assertEquals(employees.get(0), result);
    }
    @Test
    void minSalaryEmployee_shouldThrowExceptionNotEmployeeInDepartment() {
        when(employeeService.getAll()).thenReturn(Collections.emptyList());
        assertThrows(IllegalArgumentException.class,
                () -> departmentService.maxSalaryEmoloyee(1));
    }

    @Test
    void getEmployeesInDepartment_shouldReturnEmployeesWhenEmployeesInDepartment() {
        when(employeeService.getAll()).thenReturn(employees);
        Collection<Employee> result = departmentService.getEmployeesInDepartment(employees.get(0).getDepartmentId());
        assertEquals(List.of(employees.get(0), employees.get(1)), result);
    }
    @Test
    void getEmployeeInDepartment_shouldReturnEmptyListIfNotEmployeesInDepartment() {
        int department = 1;
        List<Employee> emptyList = new ArrayList<>();
        when(employeeService.getAll()).thenReturn(emptyList);
        Collection<Employee> result = departmentService.getEmployeesInDepartment(department);
        assertEquals(0,result.size());
    }

    @Test
    void getAll_shouldReturnMapWithEmployeeWhenEmployeeInDepartment() {
        when(employeeService.getAll()).thenReturn(employees);
        Map<Integer, List<Employee>> expectedMap = Map.of(
                employees.get(1).getDepartmentId(), List.of(employees.get(0), employees.get(1)),
                employees.get(2).getDepartmentId(), List.of(employees.get(2)));
        Map<Integer, List<Employee>> result = departmentService.getAll();
        assertEquals(expectedMap, result);
    }

    @Test
    void getAll_shouldReturnEmptyMapIfNotEmployeesInDepartment() {
        List<Employee> emptyList = new ArrayList<>();
        when(employeeService.getAll()).thenReturn(emptyList);
        Map<Integer, List<Employee>> result = departmentService.getAll();
        assertEquals(0, result.size());
    }

}
