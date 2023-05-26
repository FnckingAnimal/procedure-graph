package com.example.graph.constvalue;

public interface HintMessage {
    public static final String SUCCESS = "成功";
    public static final String DEPARTMENT_NAME_NULL = "部门名不能为空";
    public static final String FACTORY_EXIST = "该厂区已存在";
    public static final String FACTORY_NOT_EXIST = "厂区不存在";
    public static final String CREATE_DEPARTMENT_EXIST = "该部门已存在，请勿重复创建";
    public static final String GET_DEPARTMENTS_BY_FACTORY_ID_EMPTY = "该厂区下无部门数据";
    public static final String CREATE_MACHINE_EXIST = "该机种已存在，请勿重复创建";
    public static final String UPDATE_MACHINE_EXIST = "该机种已存在，无法修改";
    public static final String MACHINES_NOT_EXIST_BY_DEPARTMENT = "该部门无关联的机种";
    public static final String MACHINES_NOT_EXIST_BY_ID = "该机种不存在";
    public static final String DELETE_MACHINE_FAIL = "存在关联的部门，无法删除该机种";
    public static final String GET_ITEM_NOT_EXIST = "未查询到流程图";
    public static final String DEPARTMENT_NULL_HINT = "部门不能为空";
    public static final String FACTORY_NULL_HINT = "厂区不能为空";
    public static final String MACHINE_NULL_HINT = "机种不能为空";
    public static final String CREATE_ITEM_EXIST = "已存在流程图，请勿重复创建";
    public static final String DELETE_FACTORY_FAIL_BY_DEPARTMENT = "存在关联的部门，无法删除";
    public static final String DELETE_FACTORY_FAIL_BY_ITEM = "存在关联的流程图，无法删除";
}
