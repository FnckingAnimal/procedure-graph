package com.example.graph.entity.result;

import com.example.graph.entity.table.Link;
import com.example.graph.entity.table.Node;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class ItemDTO {
    /*
    item信息
     */
    private Integer itemId;
    private String itemDesc;
    private Date itemUpdateDate;
    private Integer departmentId;
    private Integer machineId;

    /*
    厂区信息
     */
    private String factoryName;
    private Integer factoryId;

    /*
    部门信息
     */
    private String departmentName;
    private String departmentDesc;
    private java.sql.Date departmentUpdateDate;

    /*
    机种信息
     */
    private String machineName;

    /*
    流程图节点信息
     */
    private List<Node> nodes;

    /*
    流程图link信息
     */
    private List<Link> links;

    /*
    图例节点信息
     */
    private List<Node> exampleNodes;
}
