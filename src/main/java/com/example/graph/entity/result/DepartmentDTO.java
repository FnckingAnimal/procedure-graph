package com.example.graph.entity.result;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
@Data
@NoArgsConstructor
public class DepartmentDTO {
    private Integer departmentId;
    private String departmentName;
    @JSONField(format = "YYYY-MM-dd")
    private Date departmentUpdateDate;
    private Integer factoryId;
    private String factoryName;
    private List<MachineDTO> machines;
    private List<ItemDTO> items;
}
