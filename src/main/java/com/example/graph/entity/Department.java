package com.example.graph.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Department {

  private String departmentName;
  @TableField("desc_")
  private String desc;
  private java.sql.Date updateDate;
  private long factoryId;
  @TableId(value = "id",type = IdType.AUTO)
  private int id;

}
