package com.example.graph.service;

import com.alibaba.fastjson2.JSONObject;
import com.example.graph.entity.Factory;
import com.example.graph.entity.result.ResponseEntity;
import org.springframework.stereotype.Service;

public interface FactoryService{
    public Integer createFactory(String name);

    public Factory getFactoryByName(String name);

    public Factory getFactoryById(Integer id);
}
