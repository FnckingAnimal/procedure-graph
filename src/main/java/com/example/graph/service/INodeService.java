package com.example.graph.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graph.entity.table.Node;
import com.example.graph.mapper.NodeMapper;

import javax.annotation.Resource;

public interface INodeService extends IService<Node> {
    void deleteByItemId(Integer itemId);
}
