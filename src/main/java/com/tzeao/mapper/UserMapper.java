package com.tzeao.mapper;

import com.tzeao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author 君子慎独
 * @create 2021/8/21 0021 0:19
 */
public interface UserMapper extends JpaRepository<User,Long> {

    User findAllByUsernameAndPassword(String username,String password);
}
