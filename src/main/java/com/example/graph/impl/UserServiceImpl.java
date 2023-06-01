package com.example.graph.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graph.entity.table.User;
import com.example.graph.mapper.UserMapper;
import com.example.graph.service.IUserService;
import com.example.graph.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User getUser(User user) {
        String employeeId = user.getEmployeeId();
        Integer id = user.getId();
        if (Utils.isNotNull(id)) {
            return userMapper.selectById(id);
        }
        return userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getEmployeeId, employeeId));
    }

    @Override
    public void updateUser(User user) {
        updateById(user);
    }
}
