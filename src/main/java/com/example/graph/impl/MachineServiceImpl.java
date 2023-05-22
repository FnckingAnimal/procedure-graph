package com.example.graph.impl;

import com.example.graph.controller.BaseController;
import com.example.graph.entity.Machine;
import com.example.graph.mapper.MachineMapper;
import com.example.graph.service.MachineService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MachineServiceImpl extends BaseImpl implements MachineService  {
    @Override
    public Integer createMachine(Machine machine) {
        machineMapper.insert(machine);
        return machine.getId();
    }

    @Override
    public Machine getMachine(Integer id, String name) {
        return null;
    }

    @Override
    public List<Machine> getMachinesByDepartmentId(Integer departmentId) {
        return null;
    }

    @Override
    public List<Machine> getMachinesByFactoryId(Integer factoryId) {
        return null;
    }
}
