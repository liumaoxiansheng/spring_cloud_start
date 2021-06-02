package com.demo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@TableName("student")
public class Student {
    @Id
private long id;
private String name;
private LocalDateTime time;
}
