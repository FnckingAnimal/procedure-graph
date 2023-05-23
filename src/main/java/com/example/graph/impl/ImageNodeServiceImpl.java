package com.example.graph.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.table.ImageNode;
import com.example.graph.mapper.ImageNodeMapper;
import com.example.graph.service.IImageNodeService;
import org.springframework.stereotype.Service;

@Service
public class ImageNodeServiceImpl extends ServiceImpl<ImageNodeMapper, ImageNode>implements IImageNodeService {
}
