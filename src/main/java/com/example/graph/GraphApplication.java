package com.example.graph;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.graph.mapper")
public class GraphApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphApplication.class, args);
    }

}
