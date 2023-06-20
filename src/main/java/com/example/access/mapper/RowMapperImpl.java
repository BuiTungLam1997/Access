package com.example.access.mapper;

import com.example.access.annatation.Column;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperImpl<T> implements RowMapper<T> {

    @Override
    public T mapRow(ResultSet resultSet) {
        return null;
    }

    @Override
    public <E> E mapRowEntity(ResultSet resultSet, Class<E> zclass) {
        try {
            E result = zclass.newInstance();
            Field[] fields = zclass.getDeclaredFields();
            for (Field field: fields) {
                Column column = field.getDeclaredAnnotation(Column.class);
                if (column == null) {
                    continue;
                }
                Object value = resultSet.getObject(column.name(), field.getType());
                field.setAccessible(true);
                field.set(result, value);
            }
            Class<?> superClass = zclass.getSuperclass();
            while (superClass != null) {
                fields = superClass.getDeclaredFields();
                for (Field field: fields) {
                    Column column = field.getDeclaredAnnotation(Column.class);
                    if (column == null) {
                        continue;
                    }
                    Object value = resultSet.getObject(column.name(), field.getType());
                    field.setAccessible(true);
                    field.set(result, value);
                }
                superClass = superClass.getSuperclass();
            }
            return result;
        } catch (InstantiationException | IllegalAccessException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
