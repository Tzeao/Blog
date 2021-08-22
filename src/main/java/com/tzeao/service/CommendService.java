package com.tzeao.service;

import com.tzeao.entity.Comment;

import java.util.List;

/**
 * @Author 君子慎独
 * @create 2021/8/23 0023 0:58
 */
public interface CommendService {

    List<Comment> listCommendByBlogId(Long id);

    Comment saveCommend(Comment comment);
}
