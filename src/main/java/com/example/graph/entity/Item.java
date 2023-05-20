package com.example.graph.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Item {

  private long id;
  @TableField("desc_")
  private String desc;
  private java.sql.Date updateDate;
  private long departmentId;
  private long machineId;

}
