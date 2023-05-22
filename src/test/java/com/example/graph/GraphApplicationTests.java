package com.example.graph;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.graph.entity.Department;
import com.example.graph.entity.Factory;
import com.example.graph.entity.Item;
import com.example.graph.entity.result.FactoryDTO;
import com.example.graph.entity.result.ItemDTO;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.mapper.DepartmentMapper;
import com.example.graph.mapper.ItemMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@SpringBootTest
class GraphApplicationTests {
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    void contextLoads() {
        MPJLambdaWrapper<Item> wrapper = new MPJLambdaWrapper<Item>()
                .selectAll(Item.class)
                .selectAs(Department::getDepartmentName,ItemDTO::getDepartmentName)
                .selectAs(Department::getDesc,ItemDTO::getDepartmentDesc)
                .selectAs(Department::getUpdateDate,ItemDTO::getDepartmentUpdateDate)
                .leftJoin(Department.class, Department::getId, Item::getDepartmentId);
        List<ItemDTO> itemDTOList = itemMapper.selectJoinList(ItemDTO.class,wrapper);
        JSONObject jo = new JSONObject();
        jo.put("data",itemDTOList);
        System.out.println(JSON.toJSONString(jo));
    }
    @Test
    void departmentTest(){
        List<Department> department = departmentMapper.selectList(null);
        ResponseEntity resp = new ResponseEntity(department);
        System.out.println(department);
        System.out.println(resp.toJSONString());
    }
    @Test
    void testJson(){
        FactoryDTO factoryDTO = new FactoryDTO();
        Factory factory = new Factory();
        factory.setFactoryId(123);
        factory.setFactoryName("ndasld");
        BeanUtil.copyProperties(factory,factoryDTO);
        System.out.println(factoryDTO);
    }
}
