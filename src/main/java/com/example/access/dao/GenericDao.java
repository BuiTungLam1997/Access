package com.example.access.dao;

import com.example.access.mapper.RowMapper;

import java.util.List;

public interface GenericDao<T> {
    <T>List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
    void update(String sql,Object... parameters);
    String insert(String sql,String id,Object... parameters);
    int count (String sql,Object... parameters);
    String maxId(String sql,Object... parameters);
}
