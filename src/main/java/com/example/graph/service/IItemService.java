package com.example.graph.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graph.entity.result.ItemDTO;
import com.example.graph.entity.table.Item;

import java.util.List;

public interface IItemService extends IService<Item> {

    ItemDTO getItemById(Integer itemId,Boolean needFullInfo);

    ItemDTO getItemBasicInfo(Integer departmentId, Integer machineId);

    List<ItemDTO> getItems(Integer factoryId, Integer departmentId, Integer machineId);

    Integer createItem(Item item);

    List<ItemDTO> getItemsByFactoryId(Integer factoryId);

    void updateItemUpdateTime(JSONObject json);
}
