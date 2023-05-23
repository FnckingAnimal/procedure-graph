package com.example.graph.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.result.MachineDTO;
import com.example.graph.entity.table.Machine;
import com.example.graph.mapper.MachineMapper;
import com.example.graph.service.IMachineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineServiceImpl extends ServiceImpl<MachineMapper, Machine> implements IMachineService {
    // TODO: 2023/5/23 试一下autowired有效果没
    MachineMapper machineMapper = getBaseMapper();

    @Override
    public Integer createMachine(Machine machine) {
        machineMapper.insert(machine);
        return machine.getMachineId();
    }

    @Override
    public MachineDTO getMachine(Integer id, String name) {
        return null;
    }

    @Override
    public List<MachineDTO> getMachinesByDepartmentId(Integer departmentId) {
        return null;
    }

    @Override
    public List<MachineDTO> getMachinesByFactoryId(Integer factoryId) {
        return null;
    }

    @Override
    public void updateMachine(Machine machine) {

    }

    @Override
    public void deleteMachine(Integer machineId) {

    }
}
