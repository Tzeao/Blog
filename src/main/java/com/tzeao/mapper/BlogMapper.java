package com.tzeao.mapper;

import com.tzeao.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author 君子慎独
 * @create 2021/8/21 0021 22:55
 */
public interface BlogMapper extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {

}
