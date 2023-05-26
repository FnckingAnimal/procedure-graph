package com.example.graph.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.table.Link;
import com.example.graph.entity.table.Node;
import com.example.graph.mapper.LinkMapper;
import com.example.graph.service.ILinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements ILinkService {
    @Resource
    LinkMapper linkMapper;
    @Override
    public void deleteByItemId(Integer itemId) {
        UpdateWrapper<Link> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(Link::getItemId,itemId);
        linkMapper.delete(wrapper);
    }
}
