package com.example.graph.impl;

import com.example.graph.mapper.DepartmentMapper;
import com.example.graph.mapper.FactoryMapper;
import com.example.graph.mapper.ItemMapper;
import com.example.graph.mapper.MachineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseImpl {
    protected FactoryMapper factoryMapper;
    protected DepartmentMapper departmentMapper;
    protected MachineMapper machineMapper;
    protected ItemMapper itemMapper;

}
