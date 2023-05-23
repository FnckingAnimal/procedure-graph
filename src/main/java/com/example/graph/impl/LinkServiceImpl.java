package com.example.graph.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.table.Link;
import com.example.graph.mapper.LinkMapper;
import com.example.graph.service.ILinkService;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements ILinkService {
}
