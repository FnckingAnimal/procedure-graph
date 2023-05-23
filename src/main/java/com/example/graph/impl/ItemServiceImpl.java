package com.example.graph.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.result.ItemDTO;
import com.example.graph.entity.table.Item;
import com.example.graph.mapper.ItemMapper;
import com.example.graph.service.IItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    @Override
    public ItemDTO getItemById(Integer itemId) {
        return null;
    }

    @Override
    public ItemDTO getItem(Integer factoryId, Integer departmentId, Integer machineId) {
        return null;
    }

    @Override
    public List<ItemDTO> getItems(Integer factoryId, Integer departmentId, Integer machineId) {
        return null;
    }

    @Override
    public Integer createItem(Item item) {
        return null;
    }

}
