package com.example.graph.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.graph.entity.Department;
import com.example.graph.entity.Factory;
import com.example.graph.entity.result.ItemDTO;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<Department> {
    List<ItemDTO> getDepartmentInfo(Department department, Factory factory);

}
