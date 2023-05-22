package com.example.graph.entity.result;

import com.example.graph.entity.Link;
import com.example.graph.entity.Node;
import lombok.Data;

import java.util.List;
@Data
public class ItemDTO {
    /*
    item信息
     */
    private Integer id;
    private String desc;
    private java.sql.Date updateDate;
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
    private List<Node> nodeList;

    /*
    流程图link信息
     */
    private List<Link> linkList;

    /*
    图例节点信息
     */
    private List<Node> exampleNodeList;
}
