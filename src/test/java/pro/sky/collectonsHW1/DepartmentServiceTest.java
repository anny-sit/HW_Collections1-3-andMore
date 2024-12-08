package pro.sky.collectonsHW1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.collectonsHW1.exceptions.DepartmentNotFoundException;
import pro.sky.collectonsHW1.service.DepartmentService;
import pro.sky.collectonsHW1.service.EmployeeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentService out;
    Employee testE;
    Employee testE1;
    HashMap<String, Employee> emplMap;
    List<Employee> emplList;
    List<Employee> emplList1;
    HashMap<Integer, List<Employee>> emplMap1;

    @BeforeEach
    public void setUp() {

        testE = new Employee("Ivan", "Ivanov", 100.1, 1);
        testE1 = new Employee("Oleg", "Ivanov", 100.1, 2);
        emplMap = new HashMap<>() {{
            put("Ivan Ivanov", testE);
            put("Oleg Ivanov", testE1);
        }};

        emplList = new ArrayList<>(List.of(testE));
        emplList1 = new ArrayList<>(List.of(testE1));

        emplMap1 = new HashMap<>() {{
            put(1, emplList);
            put(2, emplList1);
        }};
    }


    @Test
    public void getDepMonthlySalarySumTest() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(emplMap);
        assertEquals(100.1, out.getDepMonthlySalarySum(1));
        assertEquals(100.1, out.getDepMonthlySalarySum(2));
    }

    @Test
    public void getDepMinSalaryTest() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(emplMap);
        assertEquals(100.1, out.getDepMinSalary(1));
        assertEquals(100.1, out.getDepMinSalary(2));
    }

    @Test
    public void getDepMaxSalaryTest() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(emplMap);
        assertEquals(100.1, out.getDepMaxSalary(1));
        assertEquals(100.1, out.getDepMaxSalary(2));
    }

    @Test
    public void getDepEmployeesListTest() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(emplMap);
        assertEquals(emplList, out.getDepEmployeesList(1));
    }

    @Test
    public void getSortedByDeptEmployeesListTest() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(emplMap);
        assertEquals(emplMap1, out.getSortedByDeptEmployeesList());

    }

    @Test
    public void methodsTrowsExceptionsDepartmentNotFound() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(emplMap);
        assertThrows(DepartmentNotFoundException.class, () -> out.getDepEmployeesList(3));
        assertThrows(DepartmentNotFoundException.class, () -> out.getDepMaxSalary(3));
        assertThrows(DepartmentNotFoundException.class, () -> out.getDepMinSalary(3));
        assertThrows(DepartmentNotFoundException.class, () -> out.getDepMonthlySalarySum(3));
    }

    @Test
    public void methodsTrowsExceptionsNullPointer() {
        when(employeeServiceMock.getEmployees())
                .thenReturn(null);
        assertThrows(NullPointerException.class, () -> out.getDepEmployeesList(2));
        assertThrows(NullPointerException.class, () -> out.getDepMaxSalary(2));
        assertThrows(NullPointerException.class, () -> out.getDepMinSalary(2));
        assertThrows(NullPointerException.class, () -> out.getDepMonthlySalarySum(2));
    }

}
