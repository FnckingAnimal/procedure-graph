package com.example.graph.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graph.entity.result.FactoryDTO;
import com.example.graph.entity.table.Factory;

import java.util.List;

public interface IFactoryService extends IService<Factory> {
    public Integer createFactory(String name);

    public FactoryDTO getFactoryByName(String name);

    public FactoryDTO getFactoryById(Integer id);

    public List<FactoryDTO> getAllFactories();
}
