package com.example.graph;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graph.controller.ItemController;
import com.example.graph.entity.result.DepartmentDTO;
import com.example.graph.entity.table.*;
import com.example.graph.entity.result.FactoryDTO;
import com.example.graph.entity.result.ItemDTO;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.mapper.DepartmentMapper;
import com.example.graph.mapper.FactoryMapper;
import com.example.graph.mapper.ItemMapper;
import com.example.graph.service.IDepartmentService;
import com.example.graph.service.IImageNodeService;
import com.example.graph.service.IItemService;
import com.example.graph.utils.Utils;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import jdk.nashorn.internal.scripts.JO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@SpringBootTest
@Slf4j
class GraphApplicationTests {
    @Autowired
    IItemService itemService;
    @Resource
    ItemMapper itemMapper;
    @Autowired
    IDepartmentService departmentService;
    @Autowired
    IImageNodeService imageNodeService;

    @Test
    void testGetItem() {
        Department department = new Department();
        department.setDepartmentId(4);
        department.setDepartmentName("longhuabumen2");
        department.setFactoryId(1);
        long start = System.currentTimeMillis();
        ArrayList<Long> longs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            long s = System.currentTimeMillis();
            departmentService.updateDepartment(department);
            long e = System.currentTimeMillis();
            long l = (e - s);
            longs.add(l);
        }
        long end = System.currentTimeMillis();
        System.out.println("*************************");
        System.out.println((end - start) / 1000);
        System.out.println(longs);
        System.out.println("*************************");

    }

    @Test
    void test() {
        List<Integer> list = new ArrayList<>();
        JSONObject json = new JSONObject();
        json.put("links", list);
        String str = json.toString();
        System.out.println(str);
        JSONObject jsonObject = JSON.parseObject(str);
        JSONArray links = jsonObject.getJSONArray("links");
        System.out.println(links);
        System.out.println(Utils.isNull(links));
    }
}
