package com.cetys.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by edwin on Jun, 2020
 */
@Controller
@RequestMapping("/departments")
public class DepartmentController {
    final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;

        departmentRepository.save(new Department("Engineer"));
//        departmentRepository.save(new Department("Administration"));
        departmentRepository.save(new Department("Sales"));
    }

    // TODO: CRUD controllers
    @GetMapping("/")
    String listDepartments(){
        return "";
    }
}
