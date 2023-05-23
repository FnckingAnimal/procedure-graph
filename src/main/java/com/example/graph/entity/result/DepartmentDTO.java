package com.example.graph.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
@Data
@NoArgsConstructor
public class DepartmentDTO {
    private Integer departmentId;
    private String departmentName;
    private String departmentDesc;
    private Date departmentUpdateDate;
    private Integer factoryId;
    private String factoryName;
    private List<MachineDTO> machines;
    private List<ItemDTO> items;
}
