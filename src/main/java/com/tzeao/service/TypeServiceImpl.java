package com.tzeao.service;

import com.tzeao.entity.Type;
import com.tzeao.error.NotFoundException;
import com.tzeao.mapper.TypeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 君子慎独
 * @create 2021/8/21 0021 16:30
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeMapper typeMapper;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeMapper.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeMapper.getById(id);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeMapper.findAll(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeMapper.getById(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type, t);
        return typeMapper.save(t);
    }


    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteType(Long id) {
        try {
            typeMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Type> listType() {
        return typeMapper.findAll();
    }

    @Override
    public Type findByName(String name) {
        return typeMapper.findByName(name);
    }
}
