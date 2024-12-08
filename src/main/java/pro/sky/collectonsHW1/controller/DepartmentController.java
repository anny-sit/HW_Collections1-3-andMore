package pro.sky.collectonsHW1.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.collectonsHW1.Employee;
import pro.sky.collectonsHW1.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping
    public String hello() {
        return "hey dept";
    }

    @GetMapping("/{id}/salary/max")
    public Double maxSalary(@PathVariable("id") int department) {
        return departmentService.getDepMaxSalary(department);
    }

    @GetMapping("/{id}/salary/min")
    public Double minSalary(@PathVariable("id") int department) {
        return departmentService.getDepMinSalary(department);
    }
    @GetMapping("/{id}/salary/sum")
    public Double sumSalary(@PathVariable("id") int department) {
        return departmentService.getDepMonthlySalarySum(department);
    }
    @GetMapping("/{id}/employees")
    public List<Employee> allByDept(@PathVariable(value = "id", required = false) int department) {
        return departmentService.getDepEmployeesList(department);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> all() {
        return departmentService.getSortedByDeptEmployeesList();
    }


}
