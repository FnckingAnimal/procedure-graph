package com.example.graph.entity.table;


import com.alibaba.fastjson2.annotation.JSONField;
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
  @TableField("update_date")
  @JSONField(format = "YYYY-MM-dd")
  private Date departmentUpdateDate;
  private Integer factoryId;

}
