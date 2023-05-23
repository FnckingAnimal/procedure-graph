package com.example.graph.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.result.DepartmentDTO;
import com.example.graph.entity.table.Department;
import com.example.graph.mapper.DepartmentMapper;
import com.example.graph.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public DepartmentDTO getDepartmentById(Integer id) {
        Department department = departmentMapper.selectById(id);
        return BeanUtil.copyProperties(department, DepartmentDTO.class);
    }

    @Override
    public List<DepartmentDTO> getDepartmentsByFactoryId(Integer factoryId) {
        return null;
    }

    @Override
    public DepartmentDTO getDepartmentByFactoryIdAndName(Integer factoryId, String departmentName) {
        return null;
    }

    @Override
    public Integer createDepartment(Department department) {
        departmentMapper.insert(department);
        return department.getDepartmentId();
    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartment(Integer departmentId) {

    }

    @Override
    public List<DepartmentDTO> getDepartmentsByMachineId(Integer machineId) {
        return null;
    }
}
