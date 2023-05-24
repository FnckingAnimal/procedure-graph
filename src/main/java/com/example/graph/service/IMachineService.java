package com.example.graph.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graph.entity.result.MachineDTO;
import com.example.graph.entity.table.Machine;

import java.util.List;
public interface IMachineService extends IService<Machine> {
    Integer createMachine(Machine machine);

    MachineDTO getMachineByName( String name);
    List<MachineDTO> getMachinesByDepartmentId(Integer departmentId);

    List<MachineDTO> getMachinesByFactoryId(Integer factoryId);

    void updateMachine(Machine machine);

    void deleteMachine(Integer machineId);
}
