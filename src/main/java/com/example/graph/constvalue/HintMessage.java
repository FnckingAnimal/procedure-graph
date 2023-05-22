package com.example.graph.constvalue;

public interface HintMessage {
    public static final String SUCCESS = "成功";
    public static final String CREATE_FACTORY_EXIST = "该厂区已存在，请勿重复创建";
    public static final String FACTORY_NOT_EXIST = "厂区不存在";
    public static final String CREATE_DEPARTMENT_EXIST = "该部门已存在，请勿重复创建";
    public static final String GET_DEPARTMENTS_BY_FACTORY_ID_EMPTY = "该厂区下无部门数据";
    public static final String CREATE_MACHINE_EXIST = "该机种已存在，请勿重复创建";
    public static final String MACHINES_NOT_EXIST_BY_DEPARTMENT = "该部门无关联的机种";
}
