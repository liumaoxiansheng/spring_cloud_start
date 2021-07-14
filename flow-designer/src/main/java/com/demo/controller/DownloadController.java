package com.demo.controller;

import com.demo.utils.FileDownLoadUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @className: DownloadController
 * @description: TODO 类描述
 * @author: th_legend
 * @date: 2021/7/7
 **/
@RestController
public class DownloadController {

    @GetMapping("/download")
    public void download(HttpServletResponse response, HttpServletRequest request){
        List<String> list = Arrays.asList(
                "http://ngnix.download.c061d490d82d24bbca33ebbb750c110b5.cn-shenzhen.alicontainer.com/temp/工资统计(2021-07-07-16-19-07).xls"
                , "http://ngnix.download.c061d490d82d24bbca33ebbb750c110b5.cn-shenzhen.alicontainer.com/temp/工资统计(2021-07-07-16-19-46).xls"
                ,"http://ngnix.download.c061d490d82d24bbca33ebbb750c110b5.cn-shenzhen.alicontainer.com/temp/人员进场周报表(2021-07-07-17-22-04).xls"
        );
        List<Map<String,Object>> fileList=new ArrayList<>(list.size());
        String zipFileName=null;
        for (String path : list) {
            Map<String, Object> map = new HashMap<>(8);
            String fileName = path.substring(path.lastIndexOf("/")+1);
            if (null==zipFileName) {
                zipFileName=fileName+ LocalDateTime.now().toString();
            }
            map.put("name",fileName);
            map.put("path",path);
            System.out.println("fileName = " + fileName);
            fileList.add(map);
        }

        try {
            FileDownLoadUtil.download(fileList,response,request,zipFileName);
        } catch (IOException e) {
            System.out.println("下载异常：：："+e);
        }

        // return "OK";
    }


    @GetMapping("/test")
    public String test(){
        return "hello flow";
    }
}
