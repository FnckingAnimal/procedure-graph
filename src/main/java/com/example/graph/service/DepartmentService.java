package com.example.graph.service;

import com.example.graph.entity.Department;
import com.example.graph.entity.Factory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    public Department getDepartmentById(Integer id);

    public List<Department> getDepartmentsByFactoryId(Integer factoryId);

    public Integer createDepartment(Department department);

    public void updateDepartment(Department department);

    public void deleteDepartment(Integer departmentId);
}