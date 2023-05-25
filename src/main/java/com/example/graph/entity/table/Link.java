package com.example.graph.entity.table;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Link {
    @TableId
    private String id;
    @TableField("item_id")
    private Integer itemId;
    private String startId;
    private String endId;
    private Integer startY;
    private Integer startX;
    private Integer endY;
    private Integer endX;

}
