package com.example.graph.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.table.MachineDepartment;
import com.example.graph.mapper.MachineDepartmentMapper;
import com.example.graph.service.IMachineDepartmentService;
import com.example.graph.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class MachineDepartmentServiceImpl extends ServiceImpl<MachineDepartmentMapper, MachineDepartment> implements IMachineDepartmentService {
    @Override
    public void updateMachineDepartment(Integer machineId, List<Integer> departmentIds) {
        Map<String,Object> condition = new HashMap<>();
        condition.put("machine_id",machineId);
        removeByMap(condition);

        List<MachineDepartment> machineDepartmentList = new ArrayList<>();
        if (Utils.isEmpty(departmentIds)){
            return;
        }
        for (Integer departmentId : departmentIds) {
            MachineDepartment machineDepartment = new MachineDepartment();
            machineDepartment.setMachineId(machineId);
            machineDepartment.setDepartmentId(departmentId);
            machineDepartmentList.add(machineDepartment);
        }
        saveOrUpdateBatch(machineDepartmentList);
    }
}
