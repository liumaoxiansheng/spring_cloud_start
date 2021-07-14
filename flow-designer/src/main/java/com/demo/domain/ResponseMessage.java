package com.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.ConstructorArgs;

/**
 * @className: ResponseMessage
 * @description: 返回的消息体
 * @author: th_legend
 * @date: 2021/7/7
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
    private String id;
    private String name;
    private String content;
}
