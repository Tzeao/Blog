package com.tzeao.service;

import com.tzeao.entity.Tags;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author 君子慎独
 * @create 2021/8/21 0021 16:27
 */
public interface TagService {
    Tags saveTag(Tags tags);

    Tags getTag(Long id);

    Page<Tags> listTag(Pageable pageable);

    Tags updateTag(Long id, Tags tags);

    void deleteTags(Long id);

    Tags findByName(String name);
}
