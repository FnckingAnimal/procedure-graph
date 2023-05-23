package com.example.graph.entity.result;

import lombok.Data;

import java.util.List;

@Data
public class FactoryDTO {
    private Integer factoryId;
    private String factoryName;
    private List<DepartmentDTO> departments;
}
