package com.tzeao.service;

import com.tzeao.entity.Blog;
import com.tzeao.entity.Type;
import com.tzeao.error.NotFoundException;
import com.tzeao.mapper.BlogMapper;
import com.tzeao.utils.MyBeanUtils;
import com.tzeao.vo.BlogQuery;
import org.apache.catalina.mbeans.MBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.plaf.metal.MetalButtonUI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 君子慎独
 * @create 2021/8/21 0021 22:54
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog getBolg(Long id) {
        return blogMapper.getById(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogMapper.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                }

                if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }
                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                CriteriaQuery<?> where = cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogMapper.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {

        return blogMapper.findTopBy(query,pageable);
    }

    @Override
    public List<Blog> listBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogMapper.findTopBy(pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }
        return blogMapper.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog byId = blogMapper.getById(id);
        if (blog == null) {
            throw new NotFoundException("没有找到你需要的博客");
        }
        BeanUtils.copyProperties(blog, byId, MyBeanUtils.getNullPropertyNames(blog));
        blog.setUpdateTime(new Date());
        return blogMapper.save(byId);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteBole(Long id) {
        try {
            blogMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
