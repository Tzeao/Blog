package com.tzeao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author 君子慎独
 * @create 2021/8/20 0020 18:29
 */
@Entity
@Table(name = "tz_tags")
public class Tags {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Tags() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
