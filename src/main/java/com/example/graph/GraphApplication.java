package com.example.graph;

import com.alibaba.fastjson2.JSON;
import com.example.graph.entity.Department;
import com.example.graph.entity.Item;
import com.example.graph.entity.result.ItemDTO;
import com.example.graph.mapper.ItemMapper;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@MapperScan("com.example.graph.mapper")
public class GraphApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphApplication.class, args);
    }

}
