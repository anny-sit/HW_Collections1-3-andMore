package pro.sky.collectonsHW1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.collectonsHW1.exceptions.EmployeeAlreadyAddedException;
import pro.sky.collectonsHW1.exceptions.EmployeeNotFoundException;
import pro.sky.collectonsHW1.exceptions.EmployeeStorageIsFullException;
import pro.sky.collectonsHW1.service.EmployeeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();


    @Test
    public void addTest() {
        Employee expected = new Employee("Ivan", "Ivan", 100.1, 1);
        Employee real = employeeService.add("Ivan", "Ivan", 100.1, 1);
        assertEquals(expected, real);
    }

    @Test
    public void findTest() {
        employeeService.add("Ivan", "Ivan", 100.1, 1);

        Employee expected = new Employee("Ivan", "Ivan", 100.1, 1);
        Employee real = employeeService.find("Ivan", "Ivan", 100.1, 1);

        assertEquals(expected, real);
    }

    @Test
    public void removeTest() {
        Employee expected = new Employee("Ivan", "Ivan", 100.1, 1);

        employeeService.add("Ivan", "Ivan", 100.1, 1);
        Employee real = employeeService.remove("Ivan", "Ivan", 100.1, 1);

        assertEquals(expected, real);
    }

    @Test
    public void addEmployeeThrowsExceptionIfAlreadyExists() {
        employeeService.add("Ivan", "Ivan", 100.1, 1);
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add("Ivan", "Ivan", 100.1, 1));
    }

    @Test
    public void addEmployeeThrowsStorageIsFullExceptionTest() {
        employeeService.add("Ivan", "Ivan", 100.1, 1);
        employeeService.add("Ivan", "Ivan1", 100.1, 1);
        employeeService.add("Ivan", "Ivan2", 100.1, 1);
        employeeService.add("Ivan", "Ivan3", 100.1, 1);
        employeeService.add("Ivan", "Ivan4", 100.1, 1);
        employeeService.add("Ivan", "Ivan5", 100.1, 1);
        employeeService.add("Ivan", "Ivan6", 100.1, 1);
        employeeService.add("Ivan", "Ivan7", 100.1, 1);
        employeeService.add("Ivan", "Ivan8", 100.1, 1);
        employeeService.add("Ivan", "Ivan9", 100.1, 1);
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.add("Ivan", "Ivan11", 100.1, 1));
    }

    @Test
    public void employeeNotFoundExceptionTest() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("Ivan", "Ivan", 100.1, 1));
    }

}
