package com.example.graph.controller;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/machine")
public class MachineController extends BaseController {
    @PostMapping("/createMachine")
    public String createMachine(@RequestBody JSONObject json) {

        return null;
    }
    @GetMapping("/getMachinesByDepartmentId/{departmentId}")
    public String getMachinesByDepartmentId(@PathVariable Integer departmentId) {
        return null;
    }

    @PostMapping("/updateMachine")
    public String updateMachine(@RequestBody JSONObject json) {
        return null;
    }

    @DeleteMapping("/deleteMachineById/{id}")
    public String deleteMachine(@PathVariable Integer id){
        return null;
    }
}
