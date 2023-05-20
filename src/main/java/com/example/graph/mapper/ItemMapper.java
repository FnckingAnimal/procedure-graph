package com.example.graph.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.graph.entity.Item;
import com.example.graph.entity.result.ItemDTO;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

public interface ItemMapper extends MPJBaseMapper<Item> {
}
