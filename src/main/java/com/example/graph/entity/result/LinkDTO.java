package com.example.graph.entity.result;

import cn.hutool.core.bean.BeanUtil;
import com.example.graph.entity.table.Link;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class LinkDTO {
    private String id;
    private Integer itemId;
    private String startId;
    private String endId;
    private List<Integer> startAt = new ArrayList<>();
    private List<Integer> endAt = new ArrayList<>();

    public LinkDTO(Link link) {
        BeanUtil.copyProperties(link, this);
        startAt.add(link.getStartX());
        startAt.add(link.getStartY());
        endAt.add(link.getEndX());
        endAt.add(link.getEndY());
    }

}
