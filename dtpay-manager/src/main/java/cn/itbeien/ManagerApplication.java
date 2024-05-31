package cn.itbeien;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author beien
 * @date $DATE $TIME
 * Copyright© $YEAR beien
 */
@SpringBootApplication
@MapperScan  //扫描mybatis mapper xml
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class,args);
    }
}