package com.example.graph;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.graph.entity.Department;
import com.example.graph.entity.Item;
import com.example.graph.entity.result.ItemDTO;
import com.example.graph.mapper.ItemMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GraphApplicationTests {
    @Autowired
    ItemMapper itemMapper;

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
}
