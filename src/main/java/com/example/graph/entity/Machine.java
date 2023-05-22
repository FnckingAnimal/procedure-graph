package com.example.graph.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Machine {
  @TableId(value = "id",type = IdType.AUTO)
  private int id;
  private String machineName;


}
