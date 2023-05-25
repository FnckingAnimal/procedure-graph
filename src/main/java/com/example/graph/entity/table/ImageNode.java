package com.example.graph.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ImageNode {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String url;
    private String nodeId;

}
