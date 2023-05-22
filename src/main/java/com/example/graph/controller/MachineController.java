package com.example.graph.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.graph.constvalue.Code;
import com.example.graph.constvalue.HintMessage;
import com.example.graph.entity.Machine;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.rmi.CORBA.Util;
import java.util.List;

@Controller
@RequestMapping("/machine")
public class MachineController extends BaseController {
    @PostMapping("/createMachine")
    public String createMachine(@RequestParam String name) {
        Machine machine = machineService.getMachine(null, name);
        ResponseEntity resp = new ResponseEntity();
        if (Utils.isNotNull(machine)){
            resp.setMessage(HintMessage.CREATE_MACHINE_EXIST);
            resp.setCode(Code.FAILURE);
            resp.setData(machine);
            return resp.toJSONString();
        }
        machine.setMachineName(name);
        machineService.createMachine(machine);
        return new ResponseEntity(machine).toJSONString();
    }

    @GetMapping("/getMachinesByDepartmentId/{departmentId}")
    public String getMachinesByDepartmentId(@PathVariable Integer departmentId) {
        List<Machine> machines = machineService.getMachinesByDepartmentId(departmentId);
        ResponseEntity resp = new ResponseEntity();
        if (Utils.isEmpty(machines)){
            resp.setCode(Code.FAILURE);
            resp.setMessage(HintMessage.MACHINES_NOT_EXIST_BY_DEPARTMENT);
            return resp.toJSONString();
        }
        resp = new ResponseEntity(machines);
        return resp.toJSONString();
    }

    @PostMapping("/updateMachine")
    public String updateMachine(@RequestBody JSONObject json) {
        return null;
    }

    @DeleteMapping("/deleteMachineById/{id}")
    public String deleteMachine(@PathVariable Integer id) {
        return null;
    }
}
