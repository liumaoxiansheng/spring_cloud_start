package com.demo.controller;

import com.demo.Doctor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianhuan
 * @ClassName:DortorController
 * @Description:
 * @date 2019/5/13 - 10:03
 */
@RestController
@RequestMapping("/doctor")
public class DortorController {
    @RequestMapping("/getOne/{id}")
    public Doctor getOne(@PathVariable("id") Integer did){
        System.out.println("remoting Server......getData");
        Doctor doctor=new Doctor();
        doctor.setDoctorId(did);
        doctor.setDoctorName("风清扬");
        doctor.setDoctorJob("独孤九剑");
        System.out.println(doctor);
        return doctor;
    }

    @RequestMapping("/add")
    public Doctor add(@RequestBody Doctor doctor){
        System.out.println("");
        System.out.println(doctor);
        return doctor;
    }
}
