package com.example.graph.controller;

import com.alibaba.fastjson2.JSONObject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/item")
@Slf4j
public class ItemController {

    @GetMapping("/getAllItems")
    public String getAllItems() {
        return null;
    }

    @GetMapping("/getItemById/{id}")
    public String getItemById(@PathVariable Integer id) {
        return null;
    }

    /*
    参数可以为空，为空则查所有
     */
    @GetMapping("/getItem")
    public String getItem(@RequestParam Integer factoryId, @RequestParam Integer departmentId,
                          @RequestParam Integer machineId) {
        return null;
    }

    @PostMapping("/createItem")
    public String createItem(@RequestBody JSONObject json) {
        return null;
    }

    @PostMapping("/updateItem")
    public String updateItem(@RequestBody JSONObject json) {
        return null;
    }

    @DeleteMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable Integer id) {
        return null;
    }


}
