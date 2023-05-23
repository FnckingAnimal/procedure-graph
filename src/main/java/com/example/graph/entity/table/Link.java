package com.example.graph.entity.table;

import lombok.Data;

@Data
public class Link {

  private String id;
  private Integer itemId;
  private String startId;
  private String endId;
  private Integer startY;
  private Integer startX;
  private Integer endY;
  private Integer endX;

}
