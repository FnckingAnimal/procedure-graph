package com.example.graph;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graph.controller.ItemController;
import com.example.graph.entity.result.DepartmentDTO;
import com.example.graph.entity.table.Department;
import com.example.graph.entity.table.Factory;
import com.example.graph.entity.table.Item;
import com.example.graph.entity.result.FactoryDTO;
import com.example.graph.entity.result.ItemDTO;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.entity.table.Machine;
import com.example.graph.mapper.DepartmentMapper;
import com.example.graph.mapper.FactoryMapper;
import com.example.graph.mapper.ItemMapper;
import com.example.graph.service.IItemService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Test
    void testGetItem(){
        MPJLambdaWrapper<Item> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAsClass(Item.class,ItemDTO.class)
//                .selectAsClass(Machine.class,ItemDTO.class)
                .selectAsClass(Department.class,ItemDTO.class)
                .leftJoin(Department.class,Department::getDepartmentId,Item::getDepartmentId)
//                .leftJoin(Machine.class,Machine::getMachineId,Item::getMachineId)
                .eq("t.id",4);
        ItemDTO itemDTO = itemMapper.selectJoinOne(ItemDTO.class, wrapper);
        System.out.println(JSON.toJSONString(itemDTO));
    }
}
