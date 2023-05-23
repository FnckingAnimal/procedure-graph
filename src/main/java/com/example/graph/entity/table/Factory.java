package com.example.graph.entity.table;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

@Data
public class Factory {
  @TableId(value = "id",type = IdType.AUTO)
  private Integer factoryId;
  private String factoryName;

}
