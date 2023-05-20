package com.example.graph.impl;

import com.example.graph.mapper.DepartmentMapper;
import com.example.graph.mapper.FactoryMapper;
import com.example.graph.mapper.ItemMapper;
import com.example.graph.mapper.MachineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseImpl {
//    @Autowired
    protected FactoryMapper factoryMapper;
//    @Autowired
    protected DepartmentMapper departmentMapper;
//    @Autowired
    protected MachineMapper machineMapper;
    @Autowired
    protected ItemMapper itemMapper;

}
