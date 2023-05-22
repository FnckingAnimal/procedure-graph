package com.example.graph.entity.result;

import com.example.graph.entity.Department;
import com.example.graph.entity.Link;
import com.example.graph.entity.Machine;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class DepartmentDTO {
    private Integer id;
    private String name;
    private Integer factoryId;
    private String factoryName;
    private List<MachineDTO> machines;
    private List<ItemDTO> items;
}
