package com.example.graph.entity.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MachineDepartment {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer machineId;
    private Integer departmentId;
}
