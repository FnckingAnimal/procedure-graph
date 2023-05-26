package com.example.graph.entity.table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MachineDepartment {
    private Integer id;
    private Integer machineId;
    private Integer departmentId;
}
