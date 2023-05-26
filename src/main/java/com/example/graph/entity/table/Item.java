package com.example.graph.entity.table;

import com.alibaba.fastjson2.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;


@Data
public class Item {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer itemId;
    @TableField("desc_")
    private String itemDesc;
    @TableField("update_date")
    @JSONField(format = "YYYY-MM-dd")
    private Date itemUpdateDate;
    private Integer departmentId;
    private Integer machineId;

}
