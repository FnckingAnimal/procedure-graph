package com.example.graph.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.table.ImageNode;
import com.example.graph.entity.table.Item;
import com.example.graph.entity.table.Node;
import com.example.graph.mapper.ImageNodeMapper;
import com.example.graph.mapper.ItemMapper;
import com.example.graph.mapper.NodeMapper;
import com.example.graph.service.IImageNodeService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImageNodeServiceImpl extends ServiceImpl<ImageNodeMapper, ImageNode>implements IImageNodeService {
    @Resource
    ImageNodeMapper imageNodeMapper;
    @Resource
    NodeMapper nodeMapper;
    @Resource
    ItemMapper itemMapper;
    @Override
    public void deleteByItemId(Integer itemId) {
        MPJLambdaWrapper<ImageNode> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(ImageNode.class)
                .leftJoin(Node.class,"n",Node::getId,ImageNode::getNodeId)
                .leftJoin(Item.class,"i",Item::getItemId,Node::getItemId)
                .eq("i.id",itemId);
        List<ImageNode> imageNodes = imageNodeMapper.selectJoinList(ImageNode.class, wrapper);
        removeBatchByIds(imageNodes);
    }
}
