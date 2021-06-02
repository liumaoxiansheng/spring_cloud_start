package com.demo.controller;

import com.demo.Student;
import com.demo.service.iservice.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @RequestMapping("/getOne")
    public Student getOne(@RequestParam Long id){
        System.out.println("remote get Data ....id:："+id);
        Student student = studentService.getById(id);
        return student;
    }
}
