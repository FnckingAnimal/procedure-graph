package com.example.graph.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson2.JSONObject;
import com.example.graph.constvalue.Code;
import com.example.graph.constvalue.HintMessage;
import com.example.graph.entity.result.DepartmentDTO;
import com.example.graph.entity.table.Department;
import com.example.graph.entity.result.FactoryDTO;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.utils.Utils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "*")
public class DepartmentController extends BaseController {
    @PostMapping("/createDepartment")
    public String createDepartment(@RequestBody JSONObject json) {
        ResponseEntity resp = new ResponseEntity();
        Integer factoryId = json.getInteger("factoryId");
        FactoryDTO factoryById = factoryService.getFactoryById(factoryId);
        // 1 厂区不存在的情况
        if (Utils.isNull(factoryById)) {
            resp.setCode(Code.FAILURE);
            resp.setMessage(HintMessage.FACTORY_NOT_EXIST);
            return resp.toJSONString();
        }
        String departmentName = json.getString("departmentName");
        if (StringUtils.isEmpty(departmentName)){
            return new ResponseEntity().fail(HintMessage.DEPARTMENT_NAME_NULL).toJSONString();
        }
        DepartmentDTO department = departmentService.getDepartmentByFactoryIdAndDepartmentName(factoryId, departmentName);
        // 2 厂区存在，但部门已存在
        if (Utils.isNotNull(department)) {
            resp.setCode(Code.FAILURE);
            resp.setMessage(HintMessage.CREATE_DEPARTMENT_EXIST);
            resp.setData(department);
            return resp.toJSONString();
        }
        // 3 厂区存在，部门不存在，可以成功创建
        Department dept = new Department();
        dept.setDepartmentName(departmentName);
        String updateDate = json.getString("updateDate");
        if (StringUtils.isEmpty(updateDate)) {
            dept.setDepartmentUpdateDate(new Date());
        } else {
            dept.setDepartmentUpdateDate(DateUtil.parse(updateDate));
        }

        dept.setFactoryId(factoryId);
        // mybatis-plus 的insert生成的主键自动填充进对象
        departmentService.save(dept);
        resp = new ResponseEntity(dept);
        return resp.toJSONString();
    }

    @GetMapping("/getDepartmentsByFactoryId/{factoryId}")
    public String getDepartmentsByFactoryId(@PathVariable Integer factoryId) {
        List<DepartmentDTO> departments = departmentService.getDepartmentsByFactoryId(factoryId);
        ResponseEntity resp = new ResponseEntity();
        if (Utils.isEmpty(departments)) {
            resp.setCode(Code.FAILURE);
            resp.setMessage(HintMessage.GET_DEPARTMENTS_BY_FACTORY_ID_EMPTY);
            return resp.toJSONString();
        }
        return new ResponseEntity(departments).toJSONString();
    }

    @PostMapping("/updateDepartment")
    public String updateDepartment(@RequestBody JSONObject json) {
        Department department = new Department();
        department.setDepartmentId(json.getInteger("departmentId"));
        department.setDepartmentUpdateDate(new Date());
        String departmentName = json.getString("departmentName");
        if (StringUtils.isEmpty(departmentName)) {
            return new ResponseEntity().fail(HintMessage.DEPARTMENT_NAME_NULL).toJSONString();
        }
        department.setDepartmentName(departmentName);
        department.setFactoryId(json.getInteger("factoryId"));
        departmentService.saveOrUpdate(department);
        return new ResponseEntity().success().toJSONString();
    }

    @DeleteMapping("deleteDepartment/{departmentId}")
    public String deleteDepartment(@PathVariable Integer departmentId) {
        departmentService.removeById(departmentId);
        return new ResponseEntity().success().toJSONString();
    }
}
