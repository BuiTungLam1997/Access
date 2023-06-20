package com.example.access.repository.impl;

import com.example.access.annatation.Column;
import com.example.access.annatation.Id;
import com.example.access.annatation.Table;
import com.example.access.mapper.RowMapper;
import com.example.access.mapper.RowMapperImpl;
import com.example.access.paging.Pageble;
import com.example.access.repository.Repository;
import com.example.access.repository.entity.AbstractEntity;
import com.example.access.repository.entity.DeviceEntity;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public abstract class SimpleJpaRepository<E extends AbstractEntity, ID> implements Repository<E, ID> {

    private final Class<E> zclass;
    private final RowMapper<E> mapper;
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

    public SimpleJpaRepository(Class<E> zclass) {
        this.zclass = zclass;
        this.mapper = new RowMapperImpl<>();
    }

    public Connection getConnection() {
        try {
            Class.forName(resourceBundle.getString("driverName"));
            String url = resourceBundle.getString("url");
            String username = resourceBundle.getString("username");
            String password = resourceBundle.getString("password");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    @Override
    public E update(E e) {
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(getTableName());
        sql.append(" SET ");
        sql.append(setValue(e));
        sql.append(" WHERE ");
        sql.append(getIdName()).append(" = ");
        sql.append(" ? ");


        return e;
    }

    private String setValue(E e) {
        StringBuilder setValue = new StringBuilder("");
        Field[] fields = zclass.getDeclaredFields();
        for (Field field : fields) {
            Id id = field.getDeclaredAnnotation(Id.class);
            if (id != null) {
                continue;
            }
            Column column = field.getDeclaredAnnotation(Column.class);
            if (column == null) {
                continue;
            }

            Object value = "";
            setValue.append(column.name()).append(" = ");
            setValue.append(value);
            if (field.equals(fields)) {
                break;
            }
            setValue.append(" , ");
        }
        return null;
    }

    @Override
    public List<E> findBy(HashMap hashMap) {

        Set<String> keySet = hashMap.keySet();
        List<E> result = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM ");
        sqlBuilder.append(getTableName()).append(" ");
        sqlBuilder.append("WHERE").append(" ");
        for (String key : keySet) {
            sqlBuilder.append(key).append("= ").append(hashMap.get(key));
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try (Connection connection = getConnection()) {
            preparedStatement = connection.prepareStatement(sqlBuilder.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(mapper.mapRowEntity(resultSet, zclass));
            }
            return result;
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }
        }
    }

    @Override
    public List<E> findAll(Pageble pageble) {
        List<E> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM ");
        sql.append(getTableName());
        sql.append(" ");
        sql.append(" LIMIT ").append(pageble.getOffset());
        sql.append(",").append(pageble.getLimit());

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = getConnection()) {
            preparedStatement = connection.prepareStatement(sql.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(mapper.mapRowEntity(resultSet, zclass));
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<E> findAll() {
        List<E> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM ");
        sql.append(getTableName());
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = getConnection()) {
            preparedStatement = connection.prepareStatement(sql.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(mapper.mapRowEntity(resultSet, zclass));
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public E findById(ID id) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM ");
        sqlBuilder.append(getTableName()).append(" ");
        sqlBuilder.append("WHERE").append(" ");
        sqlBuilder.append(getIdName()).append(" = ");
        sqlBuilder.append("?");

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try (Connection connection = getConnection()) {
            preparedStatement = connection.prepareStatement(sqlBuilder.toString());
            preparedStatement.setObject(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapper.mapRowEntity(resultSet, zclass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getTableName() {
        Table table = zclass.getDeclaredAnnotation(Table.class);
        if (table == null) {
            return zclass.getSimpleName();
        }
        return table.name();
    }

    private String getIdName() {
        Field[] fields = zclass.getDeclaredFields();
        for (Field field : fields) {
            Id id = field.getDeclaredAnnotation(Id.class);
            if (id == null) {
                continue;
            }
            Column column = field.getDeclaredAnnotation(Column.class);
            if (column == null) {
                throw new RuntimeException("Id not found");
            }
            return column.name();
        }
        throw new RuntimeException("Id not found");
    }

    public HashMap getFieldAndValue(Column column, Object value, E e) {
        HashMap hs = new HashMap();
        try {
            Field[] fields = DeviceEntity.class.getDeclaredFields();
            for (Field field : fields) {
                column = field.getAnnotation(Column.class);
                if (column == null) {
                    continue;
                }
                field.setAccessible(true);
                value = field.get(e);
                if (value != null) {
                    break;
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e1) {
            throw new RuntimeException(e1);
        }
        hs.put(column,value);
        return hs;
    }

}
