package com.tzeao.service;

import com.tzeao.entity.Comment;
import com.tzeao.mapper.CommendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author 君子慎独
 * @create 2021/8/23 0023 1:00
 */
@Service
public class CommendServiceImpl implements CommendService {

    @Autowired
    private CommendMapper commendMapper;

    @Override
    public List<Comment> listCommendByBlogId(Long id) {
        Sort by = Sort.by(Sort.Direction.DESC, "createTime");
        return commendMapper.findByBlogId(id, by);
    }

    @Transactional
    @Override
    public Comment saveCommend(Comment comment) {
        Long id = comment.getParentComment().getId();
        if (id != -1) {
            comment.setParentComment(commendMapper.getById(id));
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commendMapper.save(comment);
    }
}
