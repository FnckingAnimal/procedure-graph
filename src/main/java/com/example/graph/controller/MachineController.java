package com.example.graph.controller;

import com.example.graph.constvalue.Code;
import com.example.graph.constvalue.HintMessage;
import com.example.graph.entity.result.DepartmentDTO;
import com.example.graph.entity.result.MachineDTO;
import com.example.graph.entity.table.Machine;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/machine")
public class MachineController extends BaseController {
    @PostMapping("/createMachine")
    public String createMachine(@RequestParam String name) {
        MachineDTO machineDTO = machineService.getMachine(null, name);
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
    public String updateMachine(@RequestParam Integer id, @RequestParam String newName) {
        ResponseEntity resp = new ResponseEntity();
        MachineDTO machineDTO = machineService.getMachine(id, null);
        if (Utils.isNull(machineDTO)) {
            resp.fail(HintMessage.MACHINES_NOT_EXIST_BY_ID);
        }
        Machine machine = new Machine(id, newName);

        machineService.updateMachine(machine);
        return null;
    }

    @DeleteMapping("/deleteMachineById/{machineId}")
    public String deleteMachine(@PathVariable Integer machineId) {
        List<DepartmentDTO> departmentDTOS = departmentService.getDepartmentsByMachineId(machineId);
        ResponseEntity resp = new ResponseEntity();
        if (Utils.isNotEmpty(departmentDTOS)){
            resp.fail(HintMessage.DELETE_MACHINE_FAIL,departmentDTOS);
        }
        machineService.deleteMachine(machineId);
        return new ResponseEntity(null).toJSONString();
    }
}
