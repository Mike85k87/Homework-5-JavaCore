package pro.sky.homework5javacore.object;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final int departmentId;
    private final double salary;

    public Employee(String firstName, String lastName, int departmentId, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public double getSalary() {
        return salary;
    }

    public String getFullName() {

        return firstName + "  " + lastName + "  " + departmentId + "  " + salary + "  ";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return departmentId == employee.departmentId && Double.compare(salary, employee.salary) == 0
                && Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, departmentId, salary);
    }


    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentId=" + departmentId +
                ", salary=" + salary +
                '}';
    }
}
