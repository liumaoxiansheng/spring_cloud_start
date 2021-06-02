package com.demo.service;

import com.demo.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @FeignClient("doctor-service")
public interface IStudentService {
    @RequestMapping("/student/getOne")
    public Student getOne(@RequestParam("id") Long id);
}
