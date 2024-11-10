package pro.sky.collectonsHW1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.collectonsHW1.Employee;
import pro.sky.collectonsHW1.service.DepartmentService;

import java.util.ArrayList;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping
    public String hello() {
        return "hey dept";
    }

    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam("departmentId") int department) {
        return departmentService.getDepMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam("departmentId") int department) {
        return departmentService.getDepMinSalary(department);
    }
    @GetMapping("/all")
    public ArrayList<Employee> all(@RequestParam(value = "departmentId", required = false) Integer department) {
        if (department != null) {
            return departmentService.getDepEmployeesList(department);
        } else {
            return departmentService.getSortedByDeptEmployeesList();
        }
    }


}
