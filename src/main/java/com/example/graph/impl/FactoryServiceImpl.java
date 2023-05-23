package com.example.graph.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.table.Factory;
import com.example.graph.entity.result.FactoryDTO;
import com.example.graph.mapper.FactoryMapper;
import com.example.graph.service.FactoryService;
import com.example.graph.service.IFactoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryServiceImpl extends ServiceImpl<FactoryMapper,Factory> implements IFactoryService {
    FactoryMapper factoryMapper = getBaseMapper();
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
        FactoryDTO factoryDTO = new FactoryDTO();
        BeanUtils.copyProperties(factory,factoryDTO);
        return null;
    }

    @Override
    public FactoryDTO getFactoryById(Integer id) {
        QueryWrapper<Factory> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
//        return factoryMapper.selectOne(wrapper);
        return null;
    }

    @Override
    public List<FactoryDTO> getAllFactories() {
        return null;
    }
}
