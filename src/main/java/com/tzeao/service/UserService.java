package com.tzeao.service;

import com.tzeao.entity.User;

/**
 * @Author 君子慎独
 * @create 2021/8/21 0021 0:16
 */
public interface UserService {
    /**
     * 检查用户登录
     */
    User checkUser(String username,String password);
}
