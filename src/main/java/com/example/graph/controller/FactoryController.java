package com.example.graph.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.graph.constvalue.HintMessage;
import com.example.graph.entity.result.DepartmentDTO;
import com.example.graph.entity.result.FactoryDTO;
import com.example.graph.entity.result.ItemDTO;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.entity.table.Factory;
import com.example.graph.utils.Utils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factory")
@CrossOrigin(origins = "*")
public class FactoryController extends BaseController {

    @PostMapping("/createFactory")
    public String createFactory(@RequestBody JSONObject json) {
        ResponseEntity resp = new ResponseEntity();
        String factoryName = json.getString("name");
        FactoryDTO factoryByName = factoryService.getFactoryByName(factoryName);

        if (Utils.isNull(factoryByName)) {
            factoryService.createFactory(factoryName);
            factoryByName = factoryService.getFactoryByName(factoryName);

            return new ResponseEntity(factoryByName).toJSONString();
        }
        return resp.fail(HintMessage.FACTORY_EXIST).toJSONString();
    }

    @GetMapping("/getAllFactories")
    public String getAllFactories() {
        List<FactoryDTO> allFactories = factoryService.getAllFactories();
        return new ResponseEntity(allFactories).toJSONString();
    }

    @PostMapping("/updateFactory")
    public String updateFactory(@RequestBody JSONObject json) {
        // TODO: 2023/5/26
        Factory factory = new Factory();
        Integer factoryId = json.getInteger("factoryId");
        String factoryName = json.getString("factoryName");
        factory.setFactoryId(factoryId);
        factory.setFactoryName(factoryName);
        FactoryDTO factoryByName = factoryService.getFactoryByName(factoryName);
        if (Utils.isNotNull(factoryByName)) {
            return new ResponseEntity().fail(HintMessage.FACTORY_EXIST).toJSONString();
        }
        factoryService.updateById(factory);
        FactoryDTO factoryById = factoryService.getFactoryById(factoryId);
        return new ResponseEntity(factoryById).toJSONString();
    }

    @DeleteMapping("/deleteFactoryById/{factoryId}")
    public String deleteFactoryById(@PathVariable Integer factoryId) {
        List<DepartmentDTO> departmentsByFactoryId = departmentService.getDepartmentsByFactoryId(factoryId);
        if (Utils.isNotEmpty(departmentsByFactoryId)) {
            return new ResponseEntity().fail(HintMessage.DELETE_FACTORY_FAIL_BY_DEPARTMENT, departmentsByFactoryId).toJSONString();
        }
        List<ItemDTO> itemsByFactoryId = itemService.getItemsByFactoryId(factoryId);
        if (Utils.isNotEmpty(itemsByFactoryId)) {
            return new ResponseEntity().fail(HintMessage.DELETE_FACTORY_FAIL_BY_ITEM, itemsByFactoryId).toJSONString();
        }
        factoryService.deleteFactoryById(factoryId);
        return new ResponseEntity().success().toJSONString();
    }
}
