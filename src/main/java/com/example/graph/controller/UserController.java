package com.example.graph.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.graph.constvalue.HintMessage;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.entity.table.User;
import com.example.graph.utils.Utils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @PostMapping("/createUser")
    public String createUser(@RequestBody JSONObject json) {
        String employeeId = json.getString("employeeId");
        String name = json.getString("name");
        String role = json.getString("role");
        User user = new User();
        user.setEmployeeId(employeeId);
        user.setName(name);
        user.setRole(role);

        User userResult = userService.getUser(user);
        if (Utils.isNotNull(userResult)) {
            return new ResponseEntity().fail(HintMessage.USER_EXIST, userResult).toJSONString();
        }
        userService.save(user);

        return new ResponseEntity(user).toJSONString();
    }

    @GetMapping("/getUser")
    public String getUser(@RequestParam(required = false) String employeeId) {
        if (StrUtil.isEmpty(employeeId)) {
            List<User> list = userService.list();
            if (Utils.isEmpty(list)) {
                return new ResponseEntity().fail(HintMessage.GET_USER_FAIL).toJSONString();
            }
            return new ResponseEntity(list).toJSONString();
        }
        User user = new User();
        user.setEmployeeId(employeeId);
        user = userService.getUser(user);
        if (Utils.isNull(user)) {
            return new ResponseEntity().fail(HintMessage.GET_USER_FAIL).toJSONString();
        }
        return new ResponseEntity(user).toJSONString();
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody JSONObject json) {
        User user = new User();
        user.setId(json.getInteger("id"));
        user.setEmployeeId(json.getString("employeeId"));

        user = userService.getUser(user);
        user.setName(json.getString("name"));
        user.setRole(json.getString("role"));
        userService.updateUser(user);
        return new ResponseEntity(userService.getUser(user)).toJSONString();
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam(required = false) Integer id, @RequestParam(required = false) String employeeId) {
        if (Utils.isNotNull(id)) {
            userService.removeById(id);
            return new ResponseEntity().success().toJSONString();
        }
        if (Utils.isNotNull(employeeId)) {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("employee_id", employeeId);
            userService.remove(wrapper);
            return new ResponseEntity().success().toJSONString();
        }
        return new ResponseEntity().success().toJSONString();
    }
}
