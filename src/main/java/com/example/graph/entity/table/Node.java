package com.example.graph.entity.table;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Node {

  private String desc;
  @TableId("id")
  private String id;
  private String label;
  private Integer Y;
  private Integer X;
  private Integer height;
  private Integer width;
  private String name;
  private String type;
  private Integer itemId;
  private Integer flag;


}
