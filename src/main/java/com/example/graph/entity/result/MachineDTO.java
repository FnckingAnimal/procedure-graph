package com.example.graph.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MachineDTO {
    private Integer machineId;
    private String machineName;
    private List<DepartmentDTO> departments;
}
