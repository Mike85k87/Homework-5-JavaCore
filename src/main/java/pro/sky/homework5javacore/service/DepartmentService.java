package pro.sky.homework5javacore.service;

import pro.sky.homework5javacore.object.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee maxSalaryEmoloyee(int departmentId);

    Employee minSalaryEmployee(int departmentId);

    Collection<Employee> getEmployeesInDepartment(int departmentId);

    Map<Integer, List<Employee>> getAll ();
    double sumSalaryByDepartment(int department);


}