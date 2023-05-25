package com.example.graph.entity.table;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Node {
@TableField("desc_")
  private String desc;
  @TableId("id")
  private String id;
  private String label;
  private Integer y;
  private Integer x;
  private Integer height;
  private Integer width;
  private String name;
  private String type;
  @TableField("item_id")
  private Integer itemId;
  private Integer flag;


}
