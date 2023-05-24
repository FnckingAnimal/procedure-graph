package com.example.graph.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graph.entity.result.DepartmentDTO;
import com.example.graph.entity.table.Department;

import java.util.List;

public interface IDepartmentService extends IService<Department> {
    public DepartmentDTO getDepartmentById(Integer id);

    public List<DepartmentDTO> getDepartmentsByFactoryId(Integer factoryId);

    public DepartmentDTO getDepartmentByFactoryIdAndDepartmentName(Integer factoryId, String departmentName);

    public void updateDepartment(Department department);

    public void deleteDepartment(Integer departmentId);

    List<DepartmentDTO> getDepartmentsByMachineId(Integer machineId);
}