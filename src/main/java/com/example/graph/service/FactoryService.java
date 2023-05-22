package com.example.graph.service;

import com.alibaba.fastjson2.JSONObject;
import com.example.graph.entity.Factory;
import com.example.graph.entity.result.FactoryDTO;
import com.example.graph.entity.result.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FactoryService{
    public Integer createFactory(String name);

    public FactoryDTO getFactoryByName(String name);

    public FactoryDTO getFactoryById(Integer id);

    public List<FactoryDTO> getAllFactories();
}
