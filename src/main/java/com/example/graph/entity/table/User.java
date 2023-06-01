package com.example.graph.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;
  private String employeeId;
  private String name;
  private String role;

}
