package com.example.graph;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graph.entity.result.DepartmentDTO;
import com.example.graph.entity.table.Department;
import com.example.graph.entity.table.Factory;
import com.example.graph.entity.table.Item;
import com.example.graph.entity.result.FactoryDTO;
import com.example.graph.entity.result.ItemDTO;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.mapper.DepartmentMapper;
import com.example.graph.mapper.FactoryMapper;
import com.example.graph.mapper.ItemMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@SpringBootTest
@Slf4j
class GraphApplicationTests {
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    FactoryMapper factoryMapper ;

    @Test
    void contextLoads() {
        MPJLambdaWrapper<Item> wrapper = new MPJLambdaWrapper<>();
        wrapper.select(Item::getItemId)
                .select(Department::getDepartmentId)
                .leftJoin(Department.class,"dept",Department::getDepartmentId,Item::getDepartmentId)
                .eq("dept.id",1);
        List<ItemDTO> itemDTOList = itemMapper.selectJoinList(ItemDTO.class, wrapper);
        System.out.println(JSON.toJSONString(itemDTOList));
    }
    @Test
    void departmentTest(){
        Integer factoryId = 1;
        MPJLambdaWrapper<Department> wrapperDepartment = new MPJLambdaWrapper<>();
        wrapperDepartment.select(Department::getDepartmentId)
                .selectAs(Factory::getFactoryId,"factory_id")
                .leftJoin(Factory.class,Factory::getFactoryId,Department::getFactoryId)
                .eq("factory_id",factoryId);
        List<Integer> idList = departmentMapper.selectJoinList(Department.class,wrapperDepartment).stream().map(Department::getDepartmentId).collect(Collectors.toList());

        MPJLambdaWrapper<Item> wrapper = new MPJLambdaWrapper<>();
        wrapper.select(Item::getItemId,Item::getItemDesc)
                .select(Factory::getFactoryId)
                .in("department_id",idList);
        List<ItemDTO> itemDTOList = itemMapper.selectJoinList(ItemDTO.class, wrapper);
        System.out.println(JSON.toJSONString(itemDTOList));



    }
    @Test
    void testName(){
        QueryWrapper<Factory> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 1);
        System.out.println(JSONObject.toJSONString(factoryMapper.selectOne(wrapper)));
    }
    @Test
    void testJson(){
        FactoryDTO factoryDTO = new FactoryDTO();
        Factory factory = new Factory();
        factory.setFactoryId(123);
        factory.setFactoryName("ndasld");
        List<Factory> factoryList = new ArrayList<>();
        factoryList.add(factory);
        System.out.println(BeanUtil.copyToList(factoryList,FactoryDTO.class));
    }
    @Test
    void testBean(){
        Factory factory = new Factory();
        factory.setFactoryName("fname");
        factory.setFactoryId(123);
        Department department = new Department();
        department.setDepartmentName("name***");
        department.setFactoryId(156);
        department.setDepartmentUpdateDate(new Date(new java.util.Date().getTime()));
        department.setDepartmentId(456);
        List<Department> list = new ArrayList<>();
        list.add(department);
        FactoryDTO factoryDTO = BeanUtil.copyProperties(factory, FactoryDTO.class);
        System.out.println(JSON.toJSONString(factoryDTO));
    }
    @Test
    void testGetDepartmentsByFactoryId(){
        MPJLambdaWrapper<Department> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Department.class)
                .select(Factory::getFactoryName)
                .leftJoin(Factory.class, Factory::getFactoryId, Department::getFactoryId)
                .eq("factory_id", 1);
        log.info(JSON.toJSONString(departmentMapper.selectJoinList(DepartmentDTO.class, wrapper)));
    }
}
