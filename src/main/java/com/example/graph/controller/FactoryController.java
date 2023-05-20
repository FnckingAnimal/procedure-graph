package com.example.graph.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.graph.constvalue.Code;
import com.example.graph.constvalue.HintMessage;
import com.example.graph.entity.Factory;
import com.example.graph.entity.param.QueryParam;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.service.FactoryService;
import com.example.graph.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.rmi.CORBA.Util;

@Controller
@RequestMapping("/factory")
public class FactoryController extends BaseController {

    @PostMapping("/createFactory")
    public String createFactory(@RequestBody JSONObject json) {
        ResponseEntity resp = new ResponseEntity();
        resp.setMessage(HintMessage.CREATE_FACTORY_EXIST);
        resp.setCode(Code.FAILURE);

        String factoryName = json.getString("name");
        Factory factoryByName = factoryService.getFactoryByName(factoryName);
        if (Utils.isNull(factoryByName)) {
            factoryService.createFactory(factoryName);
            factoryByName = factoryService.getFactoryByName(factoryName);

            resp.setData(JSON.toJSONString(factoryByName));
            resp.setCode(Code.SUCCESS);
            resp.setMessage(HintMessage.SUCCESS);
        }
        return JSON.toJSONString(resp);
    }
    @GetMapping("/getAllFactories")
    public String getAllFactories(){
        return null;
    }



}
