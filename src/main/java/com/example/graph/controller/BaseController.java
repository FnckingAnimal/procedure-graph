package com.example.graph.controller;

import com.example.graph.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public abstract class BaseController {
    @Autowired
    IFactoryService factoryService;
    @Autowired
    IDepartmentService departmentService;
    @Autowired
    IMachineService machineService;
    @Autowired
    IItemService itemService;
    @Autowired
    INodeService nodeService;
    @Autowired
    ILinkService linkService;
    @Autowired
    IImageNodeService imageNodeService;
}
