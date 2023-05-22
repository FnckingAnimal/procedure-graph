package com.example.graph.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graph.entity.Department;
import com.example.graph.mapper.DepartmentMapper;
import com.example.graph.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("DepartmentService")
public class DepartmentServiceImpl extends BaseImpl implements DepartmentService {
    public Department getDepartmentById(Integer id){
        return departmentMapper.selectById(id);
    }

    @Override
    public List<Department> getDepartmentsByFactoryId(Integer factoryId) {
        return null;
    }

    @Override
    public Department getDepartmentByFactoryIdAndName(Integer factoryId, String departmentName) {
        return null;
    }

    @Override
    public Integer createDepartment(Department department) {
        departmentMapper.insert(department);
        return department.getId();
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartment(Integer departmentId) {

    }
}
