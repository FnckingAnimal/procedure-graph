package com.example.graph.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.graph.constvalue.Code;
import com.example.graph.constvalue.HintMessage;
import com.example.graph.entity.Factory;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {
    @PostMapping("/createDepartment")
    public String createDepartment(@RequestBody JSONObject json) {
        ResponseEntity resp = new ResponseEntity();
        Integer factoryId = json.getInteger("factoryId");
        Factory factoryById = factoryService.getFactoryById(factoryId);
        if (Utils.isNull(factoryById)) {
            resp.setCode(Code.FAILURE);
            resp.setMessage(HintMessage.FACTORY_NOT_EXIST);
            return JSON.toJSONString(resp);
        }
        return null;
    }

    @GetMapping("/getDepartmentByFactoryId/{factoryId}")
    public String getDepartmentByFactoryId(@PathVariable Integer factoryId) {
        return null;
    }

}
