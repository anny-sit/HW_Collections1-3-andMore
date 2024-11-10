package pro.sky.collectonsHW1;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private double salary;
    private int department;


    public Employee(String firstName, String lastName, double salary, int department) {

        String one = StringUtils.trim(firstName);
        String two = StringUtils.trim(lastName);

        if (StringUtils.strip(one).isEmpty() || StringUtils.strip(two).isEmpty()) {
            throw new RuntimeException(String.valueOf(HttpStatus.BAD_REQUEST));
        }

        this.firstName = StringUtils.capitalize(StringUtils.strip(one));
        this.lastName = StringUtils.capitalize(StringUtils.strip(two));
        this.salary = salary;
        this.department = department;
    }


    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Employee: " + lastName + " " + firstName;
    }
}
