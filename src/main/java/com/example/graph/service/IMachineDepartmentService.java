package com.example.graph.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graph.entity.table.MachineDepartment;

import java.util.List;

public interface IMachineDepartmentService extends IService<MachineDepartment> {
    void updateMachineDepartment(Integer machineId, List<Integer> departmentIds);
}
