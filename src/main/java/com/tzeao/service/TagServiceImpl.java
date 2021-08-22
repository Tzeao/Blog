package com.tzeao.service;

import com.tzeao.entity.Tags;
import com.tzeao.error.NotFoundException;
import com.tzeao.mapper.TagMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 君子慎独
 * @create 2021/8/21 0021 16:30
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Transactional
    @Override
    public Tags saveTag(Tags tags) {
        return tagMapper.save(tags);
    }

    @Transactional
    @Override
    public Tags getTag(Long id) {
        return tagMapper.getById(id);
    }

    @Transactional
    @Override
    public Page<Tags> listTag(Pageable pageable) {
        return tagMapper.findAll(pageable);
    }

    @Transactional
    @Override
    public Tags updateTag(Long id, Tags tags) {
        Tags t = tagMapper.getById(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(tags, t);
        return tagMapper.save(t);
    }

    @Override
    public List<Tags> listTagsTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return tagMapper.findTop(pageable);
    }

    @Override
    public List<Tags> listTags() {
        return tagMapper.findAll();
    }

    @Override
    public List<Tags> listTags(String ids) {
        return tagMapper.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteTags(Long id) {
        tagMapper.deleteById(id);
    }


    @Override
    public Tags findByName(String name) {
        return tagMapper.findByName(name);
    }
}
