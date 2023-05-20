package com.example.graph.controller;

import com.example.graph.mapper.DepartmentMapper;
import com.example.graph.mapper.FactoryMapper;
import com.example.graph.service.DepartmentService;
import com.example.graph.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public abstract class BaseController {
    @Autowired
    FactoryService factoryService;
    @Autowired
    DepartmentService departmentService;
}