package com.example.graph.entity.table;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ImageNode {
    @TableId("id")
    private long id;
    private String url;
    private String nodeId;

}
