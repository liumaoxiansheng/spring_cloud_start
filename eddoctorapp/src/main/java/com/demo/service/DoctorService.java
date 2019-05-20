package com.demo.service;

import com.demo.Doctor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author tianhuan
 * @ClassName:DoctorService
 * @Description:
 * @date 2019/5/13 - 17:07
 */
@FeignClient("doctor-service")
public interface DoctorService {

    @RequestMapping("/doctor/getOne/{id}")
    public Doctor getOne(@PathVariable("id") Integer doctorId);

    @RequestMapping("/doctor/add")
    public Doctor add(@RequestBody Doctor doctor);
}
