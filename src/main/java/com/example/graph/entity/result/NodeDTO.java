package com.example.graph.entity.result;

import cn.hutool.core.bean.BeanUtil;
import com.example.graph.constvalue.Code;
import com.example.graph.entity.table.ImageNode;
import com.example.graph.entity.table.Node;
import com.example.graph.utils.Utils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class NodeDTO {
    private String id;
    private String desc;
    private List<Integer> coordinate = new ArrayList<>();
    private Integer height;
    private Integer width;
    private Integer itemId;
    private Integer typeFlag;
    private Boolean showFlag;
    private Meta meta = new Meta();

    public NodeDTO(Node node, List<ImageNode> images) {
        this(node);
        if (Utils.isNotEmpty(images)) {
            List<String> urls = images.stream().map(ImageNode::getUrl).collect(Collectors.toList());
            meta.setImage(urls);
        }
    }

    public NodeDTO(Node node) {
        BeanUtil.copyProperties(node, this);
        BeanUtil.copyProperties(node, meta);
        if (node.getTypeFlag() == Code.EXAMPLE_NODE && Utils.isNull(node.getShowFlag())) {
            setShowFlag(false);
        }
        coordinate.add(node.getX());
        coordinate.add(node.getY());
    }
}
