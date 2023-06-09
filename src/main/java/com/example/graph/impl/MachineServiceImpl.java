package com.example.graph.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.result.ItemDTO;
import com.example.graph.entity.result.MachineDTO;
import com.example.graph.entity.table.Department;
import com.example.graph.entity.table.Item;
import com.example.graph.entity.table.Machine;
import com.example.graph.entity.table.MachineDepartment;
import com.example.graph.mapper.DepartmentMapper;
import com.example.graph.mapper.ItemMapper;
import com.example.graph.mapper.MachineDepartmentMapper;
import com.example.graph.mapper.MachineMapper;
import com.example.graph.service.IMachineService;
import com.example.graph.utils.Utils;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MachineServiceImpl extends ServiceImpl<MachineMapper, Machine> implements IMachineService {
    @Resource
    MachineMapper machineMapper;
    @Resource
    MachineDepartmentMapper machineDepartmentMapper;
    @Resource
    ItemMapper itemMapper;
    @Resource
    DepartmentMapper departmentMapper;

    @Override
    public Integer createMachine(Machine machine) {
        saveOrUpdate(machine);
        return machine.getMachineId();
    }

    @Override
    public MachineDTO getMachineByName(String name) {
        QueryWrapper<Machine> wrapper = new QueryWrapper<>();

        Machine machine = machineMapper.selectOne(wrapper.lambda().eq(Machine::getMachineName,name));
        return BeanUtil.copyProperties(machine, MachineDTO.class);
    }

    @Override
    public List<MachineDTO> getMachinesByDepartmentId(Integer departmentId) {
        MPJLambdaWrapper<Machine> wrapperMachine = new MPJLambdaWrapper<>();
        wrapperMachine.selectAll(Machine.class)
                .leftJoin(MachineDepartment.class,"md",MachineDepartment::getMachineId,Machine::getMachineId)
                .eq("md.department_id",departmentId);
        return machineMapper.selectJoinList(MachineDTO.class, wrapperMachine);
    }

    @Override
    public List<MachineDTO> getMachinesByFactoryId(Integer factoryId) {
        return null;
    }

    @Override
    public void updateMachine(Machine machine) {
        saveOrUpdate(machine);
    }

    @Override
    public void deleteMachine(Integer machineId) {
        removeById(machineId);
    }
}
