package com.example.graph.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graph.entity.Department;
import com.example.graph.mapper.DepartmentMapper;
import com.example.graph.service.DepartmentService;

public class DepartmentServiceImpl extends BaseImpl implements DepartmentService {
    public Department getDepartmentById(Integer id){
        return departmentMapper.selectById(id);
    }
}
