package com.example.graph.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.result.DepartmentDTO;
import com.example.graph.entity.table.Department;
import com.example.graph.entity.table.Factory;
import com.example.graph.mapper.DepartmentMapper;
import com.example.graph.mapper.FactoryMapper;
import com.example.graph.service.IDepartmentService;
import com.example.graph.utils.Utils;
import com.github.yulichang.base.MPJBaseMapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Resource
    DepartmentMapper departmentMapper;
    @Resource
    FactoryMapper factoryMapper;

    public DepartmentDTO getDepartmentById(Integer id) {
        Department department = departmentMapper.selectById(id);
        return BeanUtil.copyProperties(department, DepartmentDTO.class);
    }

    @Override
    public List<DepartmentDTO> getDepartmentsByFactoryId(Integer factoryId) {
        MPJLambdaWrapper<Department> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Department.class)
                .select(Factory::getFactoryName)
                .leftJoin(Factory.class, Factory::getFactoryId, Department::getFactoryId)
                .eq("factory_id", factoryId);
        return departmentMapper.selectJoinList(DepartmentDTO.class, wrapper);
    }

    @Override
    public DepartmentDTO getDepartmentByFactoryIdAndDepartmentName(Integer factoryId, String departmentName) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("factory_id", factoryId);
        paramMap.put("department_name", departmentName);
        List<Department> departments = departmentMapper.selectByMap(paramMap);
        if (Utils.isEmpty(departments)) {
            return null;
        }
        return BeanUtil.copyProperties(departments.get(0), DepartmentDTO.class);
    }

    @Override
    public void updateDepartment(Department department) {
        departmentMapper.updateById(department);
    }

    @Override
    public void deleteDepartment(Integer departmentId) {

    }

    @Override
    public List<DepartmentDTO> getDepartmentsByMachineId(Integer machineId) {

        return null;
    }
}
