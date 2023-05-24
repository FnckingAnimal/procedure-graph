package com.example.graph.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.table.Department;
import com.example.graph.entity.table.Factory;
import com.example.graph.entity.result.FactoryDTO;
import com.example.graph.entity.table.Item;
import com.example.graph.mapper.DepartmentMapper;
import com.example.graph.mapper.FactoryMapper;
import com.example.graph.mapper.ItemMapper;
import com.example.graph.service.IFactoryService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FactoryServiceImpl extends ServiceImpl<FactoryMapper, Factory> implements IFactoryService {
    FactoryMapper factoryMapper = getBaseMapper();
    @Resource
    DepartmentMapper departmentMapper;
    @Resource
    ItemMapper itemMapper;

    @Override
    public Integer createFactory(String factoryName) {
        Factory factory = new Factory();
        factory.setFactoryName(factoryName);
        return factoryMapper.insert(factory);
    }

    @Override
    public FactoryDTO getFactoryByName(String name) {
        QueryWrapper<Factory> wrapper = new QueryWrapper<>();
        wrapper.eq("factory_name", name);
        Factory factory = factoryMapper.selectOne(wrapper);

        return BeanUtil.copyProperties(factory, FactoryDTO.class);
    }

    @Override
    public FactoryDTO getFactoryById(Integer id) {
        QueryWrapper<Factory> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        Factory factory = getById(id);

        return BeanUtil.copyProperties(factory, FactoryDTO.class);
    }

    @Override
    public List<FactoryDTO> getAllFactories() {
        return BeanUtil.copyToList(list(), FactoryDTO.class);
    }

    @Override
    public void deleteFactoryById(Integer factoryId) {
        factoryMapper.deleteById(factoryId);
    }
}
