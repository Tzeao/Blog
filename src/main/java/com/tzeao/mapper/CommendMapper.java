package com.tzeao.mapper;

import com.tzeao.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author 君子慎独
 * @create 2021/8/23 0023 1:01
 */
public interface CommendMapper extends JpaRepository<Comment,Long> {

    List<Comment> findByBlogId(Long id, Sort sort);
}
