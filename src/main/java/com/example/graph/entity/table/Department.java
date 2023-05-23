package com.example.graph.entity.table;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Department {

  @TableId(value = "id",type = IdType.AUTO)
  private Integer departmentId;
  private String departmentName;
  @TableField("desc_")
  private String departmentDesc;
  private Date departmentUpdateDate;
  private Integer factoryId;

}
