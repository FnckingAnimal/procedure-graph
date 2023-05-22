package com.example.graph.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Item {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("desc_")
    private String desc;
    private java.sql.Date updateDate;
    private Integer departmentId;
    private Integer machineId;

}
