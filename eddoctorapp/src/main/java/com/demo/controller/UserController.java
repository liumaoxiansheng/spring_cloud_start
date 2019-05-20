package com.demo.controller;

import com.demo.Doctor;
import com.demo.pojo.User;
import com.demo.service.DoctorService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @author tianhuan
 * @ClassName:UserController
 * @Description:
 * @date 2019/5/8 - 18:55
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/findOne/{id}")
    @HystrixCommand(fallbackMethod = "localMethod")
    public Doctor findOne(@PathVariable("id") int id){
        System.out.println(id);
        // 通过服务名调用，eureka帮我们找
        Doctor doctor = restTemplate.getForObject("http://doctor-service/doctor/getOne/"+id, Doctor.class);
        Doctor param=new Doctor();
        param.setDoctorId(id);
        param.setDoctorName("张三丰");
        param.setDoctorJob("太极剑");
       // post请求参数体封装。
       /* MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>(){{
            add("doctorName","张三丰");
            add("doctorJob","太极剑");
            add("doctorId",id);
        }};*/
      //Doctor doctor = restTemplate.postForObject("http://doctor-service/doctor/add", param, Doctor.class);
       // Doctor doctor = doctorService.getOne(id);
        System.out.println(doctor);
        return doctor;
    }

    @RequestMapping("/add")
    @HystrixCommand(fallbackMethod = "localAddMethod",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value="true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value="10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value="10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value="50")
    })
    public Doctor add(){
        System.out.println("远程调用.....");
        // 通过服务名调用，eureka帮我们找
        // Doctor doctor = restTemplate.getForObject("http://doctor-service/doctor/getOne/"+id, Doctor.class);
        Doctor param=new Doctor();
        param.setDoctorId(888);
        param.setDoctorName("张三丰");
        param.setDoctorJob("太极剑");
        // post请求参数体封装。
       /* MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>(){{
            add("doctorName","张三丰");
            add("doctorJob","太极剑");
            add("doctorId",id);
        }};*/
        Doctor doctor = restTemplate.postForObject("http://doctor-service/doctor/add", param, Doctor.class);
       // final Doctor doctor = doctorService.add(param);
        System.out.println(doctor);
        return doctor;
    }

    private Doctor localMethod(int id,Throwable e){ // 异常捕获
        System.out.println("本地服务..localMethod..");
        Doctor param=new Doctor();
        param.setDoctorId(id);
        param.setDoctorName("古拉拉");
        param.setDoctorJob("螳螂拳");
        return param;
    }

    private Doctor localAddMethod(){
        System.out.println("本地服务..localAddMethod..");
        Doctor param=new Doctor();
        param.setDoctorId(6666);
        param.setDoctorName("古拉拉");
        param.setDoctorJob("螳螂拳");
        return param;
    }
}
