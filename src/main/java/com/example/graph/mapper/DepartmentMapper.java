package com.example.graph.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.graph.entity.table.Department;
import com.example.graph.entity.table.Factory;
import com.example.graph.entity.result.ItemDTO;
import com.github.yulichang.base.MPJBaseMapper;

import java.util.List;

public interface DepartmentMapper extends MPJBaseMapper<Department> {
    List<ItemDTO> getDepartmentInfo(Department department, Factory factory);

}
