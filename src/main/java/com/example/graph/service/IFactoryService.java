package com.example.graph.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graph.entity.result.FactoryDTO;
import com.example.graph.entity.table.Factory;

import java.util.List;

public interface IFactoryService extends IService<Factory> {
    Integer createFactory(String name);

    FactoryDTO getFactoryByName(String name);

    FactoryDTO getFactoryById(Integer id);

    List<FactoryDTO> getAllFactories();

    void deleteFactoryById(Integer factoryId);
}
