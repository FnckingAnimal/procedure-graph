package com.example.graph.entity.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MachineDTO {
    private Integer id;
    private Integer name;
    private List<DepartmentDTO> departments;
}
