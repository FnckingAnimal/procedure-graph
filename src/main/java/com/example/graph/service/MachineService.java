package com.example.graph.service;

import com.example.graph.entity.Machine;
import org.springframework.stereotype.Service;

import java.util.List;
public interface MachineService {
    public Integer createMachine(Machine machine);

    public Machine getMachine(Integer id,String name);
    public List<Machine> getMachinesByDepartmentId(Integer departmentId);

    public List<Machine> getMachinesByFactoryId(Integer factoryId);

}
