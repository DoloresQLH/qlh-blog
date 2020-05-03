package edu.uyuyue.dyblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@MapperScan("edu.uyuyue.dyblog.dao")
@SpringBootApplication
public class DyBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(DyBlogApplication.class, args);
    }

}
