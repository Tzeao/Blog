package com.tzeao.mapper;

import com.tzeao.entity.Type;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Author 君子慎独
 * @create 2021/8/21 0021 16:31
 */
public interface TypeMapper extends JpaRepository<Type,Long> {
  Type findByName(String name);
}
