package com.example.graph.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.graph.constvalue.Code;
import com.example.graph.constvalue.HintMessage;
import com.example.graph.entity.result.FactoryDTO;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/factory")
public class FactoryController extends BaseController {

    @PostMapping("/createFactory")
    public String createFactory(@RequestBody JSONObject json) {
        ResponseEntity resp = new ResponseEntity();
        String factoryName = json.getString("name");
        FactoryDTO factoryByName = factoryService.getFactoryByName(factoryName);

        if (Utils.isNull(factoryByName)) {
            factoryService.createFactory(factoryName);
            factoryByName = factoryService.getFactoryByName(factoryName);

            resp = new ResponseEntity(factoryByName);
            return resp.toJSONString();
        }
        resp.setMessage(HintMessage.CREATE_FACTORY_EXIST);
        resp.setCode(Code.FAILURE);
        return JSON.toJSONString(resp);
    }

    @GetMapping("/getAllFactories")
    public String getAllFactories() {
        List<FactoryDTO> allFactories = factoryService.getAllFactories();
        return new ResponseEntity(allFactories).toJSONString();
    }


}
