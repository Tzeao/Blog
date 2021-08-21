package com.tzeao.mapper;

import com.tzeao.entity.Tags;
import com.tzeao.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Author 君子慎独
 * @create 2021/8/21 0021 16:31
 */
public interface TagMapper extends JpaRepository<Tags,Long> {
  Tags findByName(String name);
}
