package pro.sky.homework5javacore.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.homework5javacore.exception.EmployeeAlreadyAddedException;
import pro.sky.homework5javacore.exception.EmployeeNotFoundException;
import pro.sky.homework5javacore.exception.EmployeeStorageIsFullException;
import pro.sky.homework5javacore.object.Employee;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.homework5javacore.service.EmployeeServiceImpl.EMPLOYEE_SIZE;

class EmployeeServiceImplTest {
    private EmployeeServiceImpl underTest;
    private final Employee expectedEmployee = new Employee("Mike", "Petrov", 1, 89000);

    @BeforeEach
    void beforeEach() {
        underTest = new EmployeeServiceImpl();
    }

    @Test
    void shouldAddEmployeeToListAndReturnEmployee() {

        Employee result = underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartmentId(),
                expectedEmployee.getSalary());

        assertTrue(underTest.getAll().contains(expectedEmployee));
        assertEquals(expectedEmployee, result);
    }

    @Test
    void shouldThrowExceptionWhenNotEnoughSize() {
        for (int i = 0; i < EMPLOYEE_SIZE; i++) {
            underTest.addEmployee((expectedEmployee.getFirstName() + i),
                    (expectedEmployee.getLastName() + i),
                    expectedEmployee.getDepartmentId(),
                    expectedEmployee.getSalary());
        }
        assertThrows(EmployeeStorageIsFullException.class, () -> underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartmentId(),
                expectedEmployee.getSalary()));

    }

    @Test
    void shouldThrowExceptionWhenEqualEmployeeInList() {
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartmentId(),
                expectedEmployee.getSalary());

        assertThrows(EmployeeAlreadyAddedException.class, () -> underTest.addEmployee(
                expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartmentId(),
                expectedEmployee.getSalary()));

    }

    @Test
    void shouldRemoveEmployeeAndReturn() {
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartmentId(),
                expectedEmployee.getSalary());

        Employee resultWithoutEmployee = underTest.removeEmployee(
                expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartmentId(),
                expectedEmployee.getSalary());

        assertFalse(underTest.getAll().remove(expectedEmployee));
        assertEquals(expectedEmployee, resultWithoutEmployee);


    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {
        Employee employee = new Employee("Jack", "Jackson", 1, 78000);
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartmentId(),
                expectedEmployee.getSalary());

        assertThrows(EmployeeNotFoundException.class,
                () -> underTest.removeEmployee(employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDepartmentId(),
                        employee.getSalary()));
    }

    @Test
    void shouldFindEmployeeAndReturnEmployee() {
        underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartmentId(),
                expectedEmployee.getSalary());

        Employee employee = underTest.findEmployee(
                expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(),
                expectedEmployee.getDepartmentId(),
                expectedEmployee.getSalary());

        assertTrue(underTest.getAll().contains(employee));
        assertEquals(expectedEmployee, employee);
    }

    @Test
    void ShouldThrowEmployeeNotFoundExceptionWhenFindingEmployee() {
        Employee employee = new Employee("Nik", "Bakov", 2, 56000);
        assertThrows(EmployeeNotFoundException.class, ()
                -> underTest.findEmployee(employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartmentId(),
                employee.getSalary()));
    }

    @Test
    void shouldReturnAllEmployee() {
        Employee employee = new Employee("Mike", "Mikov", 1, 200000);
        underTest.addEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                expectedEmployee.getDepartmentId(), expectedEmployee.getSalary());
        underTest.addEmployee(employee.getFirstName(), employee.getLastName(),
                employee.getDepartmentId(), employee.getSalary());

        Collection<Employee> result = underTest.getAll();

        assertTrue(result.containsAll(List.of(expectedEmployee, employee)));
    }

    @Test
    void shouldReturnEmptyListWhenEmployeeNotInList() {
        Collection<Employee> all = underTest.getAll();
        assertTrue(all.isEmpty());

    }
}
