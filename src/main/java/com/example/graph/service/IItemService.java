package com.example.graph.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graph.entity.result.ItemDTO;
import com.example.graph.entity.table.Item;

import java.util.List;

public interface IItemService extends IService<Item> {

    ItemDTO getItemById(Integer itemId);

    ItemDTO getItem(Integer factoryId, Integer departmentId, Integer machineId);

    List<ItemDTO> getItems(Integer factoryId, Integer departmentId, Integer machineId);

    Integer createItem(Item item);
}