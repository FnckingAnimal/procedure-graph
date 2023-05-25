package com.example.graph.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.result.DepartmentDTO;
import com.example.graph.entity.result.ItemDTO;
import com.example.graph.entity.result.LinkDTO;
import com.example.graph.entity.result.NodeDTO;
import com.example.graph.entity.table.*;
import com.example.graph.mapper.*;
import com.example.graph.service.IItemService;
import com.example.graph.utils.Utils;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {
    @Resource
    DepartmentMapper departmentMapper;
    @Resource
    ItemMapper itemMapper;
    @Resource
    NodeMapper nodeMapper;
    @Resource
    LinkMapper linkMapper;
    @Resource
    ImageNodeMapper imageMapper;

    @Override
    public ItemDTO getItemById(Integer itemId) {
        MPJLambdaWrapper<Item> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Item.class)
                .selectAll(Department.class)
                .selectAll(Factory.class)
                .selectAll(Machine.class)
                .leftJoin(Department.class, Department::getDepartmentId, Item::getDepartmentId)
                .leftJoin(Factory.class, Factory::getFactoryId, Department::getFactoryId)
                .leftJoin(Machine.class, Machine::getMachineId, Item::getMachineId)
                .eq("t.id", itemId);
        ItemDTO itemDTO = itemMapper.selectJoinOne(ItemDTO.class, wrapper);
        QueryWrapper<Node> wNode = new QueryWrapper<>();
        wNode.eq("item_id", itemId);
        List<Node> nodeList = nodeMapper.selectList(wNode);
        if (Utils.isNotEmpty(nodeList)) {
            List<NodeDTO> nodeDTOs = new ArrayList<>();
            for (Node node : nodeList) {
                NodeDTO nodeDTO = new NodeDTO(node);
                nodeDTOs.add(nodeDTO);
                HashMap<String, Object> map = new HashMap<>();
                map.put("node_id", node.getId());
                List<ImageNode> images = imageMapper.selectByMap(map);
                if (Utils.isNotEmpty(images)) {
                    nodeDTO.getMeta().setImage(images.stream().map(ImageNode::getUrl).collect(Collectors.toList()));
                }
            }
            itemDTO.setNodes(nodeDTOs);
        }
        QueryWrapper<Link> wLink = new QueryWrapper<>();
        wLink.eq("item_id", itemId);
        List<Link> linkList = linkMapper.selectList(wLink);
        if (Utils.isNotEmpty(linkList)) {
            List<LinkDTO> linkDTOs = new ArrayList<>();
            for (Link link : linkList) {
                LinkDTO linkDTO = new LinkDTO(link);
                linkDTOs.add(linkDTO);
            }
            itemDTO.setLinks(linkDTOs);
        }

        return itemDTO;
    }

    @Override
    public ItemDTO getItem(Integer departmentId, Integer machineId) {
        QueryWrapper<Item> wrapper = new QueryWrapper<>();
        wrapper.eq("department_id", departmentId).eq("machine_id", machineId);
        Item item = itemMapper.selectOne(wrapper);
        if (Utils.isNull(item)) {
            return null;
        }
        return getItemById(item.getItemId());
    }

    @Override
    public List<ItemDTO> getItems(Integer factoryId, Integer departmentId, Integer machineId) {
        List<ItemDTO> itemDTOs = new ArrayList<>();
        List<ItemDTO> items;
        if (Utils.isNull(departmentId) && Utils.isNull(machineId)) {
            return getItemsByFactoryId(factoryId);
        }
        MPJLambdaWrapper<Item> wrapper = new MPJLambdaWrapper<>();
        wrapper.select(Item::getItemId);

        wrapper.leftJoin(Machine.class, Machine::getMachineId, Item::getMachineId)
                .leftJoin(Factory.class, Factory::getFactoryId, Department::getFactoryId)
                .leftJoin(Department.class, Department::getDepartmentId, Item::getDepartmentId);
        if (Utils.isNotNull(machineId)){
            wrapper.eq(Machine::getMachineId,machineId);
        }
        if (Utils.isNotNull(departmentId)){
            wrapper.eq(Department::getDepartmentId,departmentId);
        }
        if (Utils.isNotNull(factoryId)){
            wrapper.eq(Factory::getFactoryId,factoryId);
        }
        items = itemMapper.selectJoinList(ItemDTO.class, wrapper);
        if (Utils.isNotEmpty(items)){
            for (ItemDTO item : items) {
                itemDTOs.add(getItemById(item.getItemId()));
            }
        }
        return itemDTOs;
    }

    @Override
    public Integer createItem(Item item) {
        return itemMapper.insert(item);
    }

    @Override
    public List<ItemDTO> getItemsByFactoryId(Integer factoryId) {
//        MPJLambdaWrapper<Department> wrapper0 = new MPJLambdaWrapper<>();
//        wrapper0.selectAll(Department.class)
//                .select(Factory::getFactoryId)
//                .leftJoin(Factory.class, Factory::getFactoryId, Department::getFactoryId)
//                .eq("factoryId", factoryId);
//        List<DepartmentDTO> departmentDTOList = departmentMapper.selectJoinList(DepartmentDTO.class, wrapper0);
//        if (Utils.isEmpty(departmentDTOList)) {
//            return null;
//        }
//        List<Integer> departmentIds = departmentDTOList.stream().map(DepartmentDTO::getDepartmentId).collect(Collectors.toList());
//        MPJLambdaWrapper<Item> wrapper = new MPJLambdaWrapper<>();
//        wrapper.selectAll(Item.class)
//                .leftJoin(Department.class, Department::getDepartmentId, Item::getDepartmentId)
//                .leftJoin(Factory.class, Factory::getFactoryId, Department::getFactoryId)
//                .in("departmentId", departmentIds);
//        List<ItemDTO> itemDTOs = itemMapper.selectJoinList(ItemDTO.class, wrapper);
//        if (Utils.isEmpty(itemDTOs)) {
//            return null;
//        }
//        return itemDTOs;
        MPJLambdaWrapper<Item> wrapper = new MPJLambdaWrapper<>();
        List<ItemDTO> itemDTOs = new ArrayList<>();
        wrapper.select(Item::getItemId)
                .select(Department::getFactoryId)
                .leftJoin(Department.class, Department::getDepartmentId, Item::getItemId)
                .leftJoin(Factory.class, Factory::getFactoryId, Department::getFactoryId)
                .eq("factory_id", factoryId);
        List<ItemDTO> items = itemMapper.selectJoinList(ItemDTO.class, wrapper);
        if (Utils.isEmpty(items)) {
            return null;
        }
        for (ItemDTO item : items) {
            itemDTOs.add(getItemById(item.getItemId()));
        }
        return itemDTOs;
    }

    @Override
    public void updateItemUpdateTime(JSONObject json) {
        Integer itemId = json.getInteger("id");
        UpdateWrapper<Item> wrapper = new UpdateWrapper<>();
        wrapper.set("update_date",new Date())
                .eq("id",itemId);
        update(wrapper);
    }
}
