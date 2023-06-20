package com.example.access.mapper;

import java.sql.ResultSet;

public interface RowMapper<T> {
    T mapRow(ResultSet resultSet);
    default <E> E mapRowEntity(ResultSet resultSet, Class<E> zclass) {
       return null;
    }
}
