package com.example.graph.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.table.Node;
import com.example.graph.mapper.NodeMapper;
import com.example.graph.service.INodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.print.DocFlavor;

@Service
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node> implements INodeService {
    @Resource
    NodeMapper nodeMapper;

    @Override
    public void deleteByItemId(Integer itemId) {
        UpdateWrapper<Node> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(Node::getItemId,itemId);
        nodeMapper.delete(wrapper);
    }
}
