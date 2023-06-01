package com.example.graph.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graph.entity.result.UserDTO;
import com.example.graph.entity.table.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

public interface IUserService extends IService<User> {

    User getUser(User user);

    void updateUser(User user);
}
