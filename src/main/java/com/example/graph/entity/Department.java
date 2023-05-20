package com.example.graph.entity;


import com.baomidou.mybatisplus.annotation.TableField;

public class Department {

  private String departmentName;
  @TableField("desc_")
  private String desc;
  private java.sql.Date updateDate;
  private long factoryId;
  private long id;


  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }


  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }


  public java.sql.Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(java.sql.Date updateDate) {
    this.updateDate = updateDate;
  }


  public long getFactoryId() {
    return factoryId;
  }

  public void setFactoryId(long factoryId) {
    this.factoryId = factoryId;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

}
