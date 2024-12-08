package pro.sky.collectonsHW1.service;

import org.springframework.stereotype.Service;
import pro.sky.collectonsHW1.Employee;
import pro.sky.collectonsHW1.exceptions.DepartmentNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public double getDepMonthlySalarySum(int department) {
        if (!hasSuchDepNumber(department)) {
            throw new DepartmentNotFoundException();
        }
        return employeeService.getEmployees().values().stream()
                .filter(employee -> (employee.getDepartment() == department))
                .map(Employee::getSalary)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    public Double getDepMinSalary(int department) {
        if (!hasSuchDepNumber(department)) {
            throw new DepartmentNotFoundException();
        }
        return employeeService.getEmployees().values().stream()
                .filter(employee -> (employee.getDepartment() == department))
                .map(Employee::getSalary)
                .sorted()
                .findFirst().orElse(0.0);
    }

    public Double getDepMaxSalary(int department) {
        if (!hasSuchDepNumber(department)) {
            throw new DepartmentNotFoundException();
        }
        return employeeService.getEmployees().values().stream()
                .filter(employee -> (employee.getDepartment() == department))
                .map(Employee::getSalary)
                .sorted(Comparator.reverseOrder())
                .findFirst().orElse(0.0);
    }


    public List<Employee> getDepEmployeesList(int department) {
        if (!hasSuchDepNumber(department)) {
            throw new DepartmentNotFoundException();
        }
        return employeeService.getEmployees().values().stream()
                .filter(employee -> (employee.getDepartment() == department))
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> getSortedByDeptEmployeesList() {
        Map<Integer, List<Employee>> readyToReturn = new HashMap<Integer, List<Employee>>();
        List<Integer> departmentsNumbers = employeeService.getEmployees().values().stream()
                .map(Employee::getDepartment)
                .toList();
        for (Integer i : departmentsNumbers) {
            readyToReturn.put(i, getDepEmployeesList(i));
        }

        return readyToReturn;
    }

    public boolean hasSuchDepNumber(int department) {
        List<Integer> departmentsNumbers = employeeService.getEmployees().values().stream()
                .map(Employee::getDepartment)
                .toList();
        if (departmentsNumbers.contains(department)) {
            return true;
        }
        return false;
    }

}
