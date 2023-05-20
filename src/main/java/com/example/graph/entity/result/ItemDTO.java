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
    private long id;
    private String desc;
    private java.sql.Date updateDate;
    private long departmentId;
    private long machineId;

    /*
    department信息
     */
    private String departmentName;
    private String departmentDesc;
    private java.sql.Date departmentUpdateDate;

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
