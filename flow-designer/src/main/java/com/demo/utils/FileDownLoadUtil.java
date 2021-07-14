package com.demo.utils;

import com.demo.constant.WebSocketConstant;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @className: FileDownLoadUtil
 * @description: 文件下载
 * @author: th_legend
 * @date: 2021/7/7
 **/
public class FileDownLoadUtil {



    /**
     * list 为需要下载的文件信息。Map中，我是将图片url作为key，name作为value。
     * */
    public static void download(List<Map<String,Object>> list,HttpServletResponse response,HttpServletRequest request,String zipFileName) throws IOException {


        SimpMessagingTemplate messagingTemplate = GetBeanUtil.getBean(SimpMessagingTemplate.class);
       //  HttpServletResponse response = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
       //  HttpServletRequest request = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getRequest();
        //根据不同浏览器进行不同的编码
        String agent = request.getHeader("USER-AGENT");
        String filenameEncoder = "";
        if (agent.contains("MSIE")) {
            // IE浏览器
            filenameEncoder = URLEncoder.encode(zipFileName, "utf-8");
            filenameEncoder = filenameEncoder.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filenameEncoder = "=?utf-8?B?"
                    + base64Encoder.encode(zipFileName.getBytes("utf-8")) + "?=";
        } else {
            // 其它浏览器
            filenameEncoder = URLEncoder.encode(zipFileName, "utf-8");
        }

        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("application/octet-stream");
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        response.setHeader("Content-Disposition", "attachment;fileName=" + filenameEncoder + ".zip");

        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
        int totalSize = list.size();
        int currentSize=0;
        for (Map<String, Object> map : list) {
            currentSize++;
            String path=(String)map.get("path");//图片地址
            String name=(String)map.get("name");//图片名称
            URL url = new URL(path);
            HttpURLConnection  connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout( 4 * 1000);
            InputStream inStream = connection.getInputStream();
            byte[] buffer = new byte[1024 * 10];
            int data = 0;
            zos.putNextEntry(new ZipEntry(name));
            while ((data = inStream.read(buffer, 0, 1024 * 10)) != -1) {
                zos.write(buffer,0,data);
            }
            inStream.close();
            double ceil = Math.ceil((1.0 * currentSize / totalSize) * 100);
            messagingTemplate.convertAndSend(WebSocketConstant.QUEUE+"/fileSize","当前下载进度：：："+ceil+"%");
        }
        zos.flush();
        zos.close();
    }

}
