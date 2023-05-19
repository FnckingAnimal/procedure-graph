package com.example.graph.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.graph.entity.Department;
import com.example.graph.entity.Factory;
import com.example.graph.entity.result.ItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper extends BaseMapper<Department> {
    List<ItemVO> getDepartmentInfo(Department department, Factory factory);

}
