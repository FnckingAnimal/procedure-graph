package com.example.graph.entity;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

@Data
public class Factory {
  @Alias("id")
  private Integer factoryId;
  private String factoryName;

}
