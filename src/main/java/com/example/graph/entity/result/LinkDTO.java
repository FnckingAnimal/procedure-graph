package com.example.graph.entity.result;

import cn.hutool.core.bean.BeanUtil;
import com.example.graph.entity.table.Link;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        try {
            startAt.add(link.getStartX());
            startAt.add(link.getStartY());
            endAt.add(link.getEndX());
            endAt.add(link.getEndY());
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

}
