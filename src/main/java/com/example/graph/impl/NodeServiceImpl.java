package com.example.graph.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.table.Node;
import com.example.graph.mapper.NodeMapper;
import com.example.graph.service.INodeService;
import org.springframework.stereotype.Service;

@Service
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node> implements INodeService {
}
