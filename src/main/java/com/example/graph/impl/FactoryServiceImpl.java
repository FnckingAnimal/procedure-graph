package com.example.graph.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graph.constvalue.Code;
import com.example.graph.constvalue.HintMessage;
import com.example.graph.entity.Factory;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.service.FactoryService;
import com.example.graph.utils.Utils;
import org.springframework.stereotype.Service;

@Service
public class FactoryServiceImpl extends BaseImpl implements FactoryService {
    @Override
    public Integer createFactory(String factoryName) {
        Factory factory = new Factory();
        factory.setFactoryName(factoryName);
        Integer insert = factoryMapper.insert(factory);
        return insert;
    }

    @Override
    public Factory getFactoryByName(String name) {
        QueryWrapper<Factory> wrapper = new QueryWrapper<>();
        wrapper.eq("factory_name", name);
        return factoryMapper.selectOne(wrapper);
    }

    @Override
    public Factory getFactoryById(Integer id) {
        QueryWrapper<Factory> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return factoryMapper.selectOne(wrapper);
    }
}
