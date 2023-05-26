package com.example.graph.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graph.constvalue.Code;
import com.example.graph.constvalue.HintMessage;
import com.example.graph.entity.result.DepartmentDTO;
import com.example.graph.entity.result.MachineDTO;
import com.example.graph.entity.table.Machine;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.entity.table.MachineDepartment;
import com.example.graph.utils.Utils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/machine")
@CrossOrigin(origins = "*")
public class MachineController extends BaseController {
//    @PostMapping("/createMachine")
//    public String createMachine(@RequestParam String name) {
//        MachineDTO machineDTO = machineService.getMachineByName(name);
//        ResponseEntity resp = new ResponseEntity();
//        if (Utils.isNotNull(machineDTO)) {
//            resp.setMessage(HintMessage.CREATE_MACHINE_EXIST);
//            resp.setCode(Code.FAILURE);
//            resp.setData(machineDTO);
//            return resp.toJSONString();
//        }
//        Machine machine = new Machine();
//        machine.setMachineName(name);
//        machineService.createMachine(machine);
//        return new ResponseEntity(machine).toJSONString();
//    }
@PostMapping("/createMachine")
public String createMachine(@RequestBody JSONObject json) {
    String name = json.getString("name");
    MachineDTO machineDTO = machineService.getMachineByName(name);
    ResponseEntity resp = new ResponseEntity();
    if (Utils.isNotNull(machineDTO)) {
        resp.setMessage(HintMessage.CREATE_MACHINE_EXIST);
        resp.setCode(Code.FAILURE);
        resp.setData(machineDTO);
        return resp.toJSONString();
    }
    Machine machine = new Machine();
    machine.setMachineName(name);
    machineService.createMachine(machine);

    List<Integer> departmentIds = json.getList("departmentIds", Integer.class);
    saveDepartmentIds(machine.getMachineId(), departmentIds);
    return new ResponseEntity(machine).toJSONString();
}
    @GetMapping("/getMachinesByDepartmentId/{departmentId}")
    public String getMachinesByDepartmentId(@PathVariable Integer departmentId) {
        List<MachineDTO> machines = machineService.getMachinesByDepartmentId(departmentId);
        ResponseEntity resp = new ResponseEntity();
        if (Utils.isEmpty(machines)) {
            resp.setCode(Code.FAILURE);
            resp.setMessage(HintMessage.MACHINES_NOT_EXIST_BY_DEPARTMENT);
            return resp.toJSONString();
        }
        resp = new ResponseEntity(machines);
        return resp.toJSONString();
    }

    @PostMapping("/updateMachine")
    public String updateMachine(@RequestBody JSONObject json) {
        Integer id = json.getInteger("id");
        String newName = json.getString("newName");
        MachineDTO machineByName = machineService.getMachineByName(newName);
        if (Utils.isNotNull(machineByName)) {
            return new ResponseEntity().fail(HintMessage.UPDATE_MACHINE_EXIST).toJSONString();
        }
        ResponseEntity resp = new ResponseEntity();
        MachineDTO machineDTO = BeanUtil.copyProperties(machineService.getById(id), MachineDTO.class);
        if (Utils.isNull(machineDTO)) {
            resp.fail(HintMessage.MACHINES_NOT_EXIST_BY_ID);
        }
        Machine machine = new Machine(id, newName);
        machineService.updateMachine(machine);
        List<Integer> departmentIds = json.getList("departmentIds", Integer.class);
        saveDepartmentIds(id, departmentIds);

        return new ResponseEntity().success().toJSONString();
    }

    private void saveDepartmentIds(Integer id, List<Integer> departmentIds) {
        if (Utils.isNotNull(departmentIds)) {
            machineDepartmentService.updateMachineDepartment(id, departmentIds);
        }
    }

    @DeleteMapping("/deleteMachineById/{machineId}")
    public String deleteMachine(@PathVariable Integer machineId) {
        List<DepartmentDTO> departmentDTOS = departmentService.getDepartmentsByMachineId(machineId);
        ResponseEntity resp = new ResponseEntity();
        if (Utils.isNotEmpty(departmentDTOS)) {
            resp.fail(HintMessage.DELETE_MACHINE_FAIL, departmentDTOS);
        }
        machineService.deleteMachine(machineId);
        QueryWrapper<MachineDepartment> wrapper = new QueryWrapper<>();
        wrapper.eq("machine_id",machineId);
        machineDepartmentService.remove(wrapper);
        return new ResponseEntity().success().toJSONString();
    }
}
