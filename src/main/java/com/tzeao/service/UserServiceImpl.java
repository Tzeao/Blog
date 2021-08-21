package com.tzeao.service;

import com.tzeao.entity.User;
import com.tzeao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 君子慎独
 * @create 2021/8/21 0021 0:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username) {
        User user = userMapper.findAllByUsername(username);
        return user;
    }
}
