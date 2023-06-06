package com.example.access.dao.impl;

import com.example.access.dao.IUserDao;
import com.example.access.mapper.DeviceMapper;
import com.example.access.mapper.UserMapper;
import com.example.access.model.DeviceModel;
import com.example.access.model.UserModel;

import java.util.List;

public class UserDao extends AbstractDAO<UserModel> implements IUserDao {

    @Override
    public UserModel findCheckUser(UserModel userModel) {
//        StringBuilder sql = new StringBuilder("SELECT * FROM user AS u ");
//        sql.append(" INNER JOIN role AS r ON r.id = u.roleid ");
//        sql.append(" WHERE  username = " + userModel.getUserName() + " AND password = " + userModel.getPassword()
//                + " AND status = 1");
//        List<UserModel> userModels = query(sql.toString(), new UserMapper());
//        return userModels.isEmpty() ? null : userModels.get(0);
        StringBuilder sql = new StringBuilder("select * from user as u ");
        sql.append(" inner join role as r on r.id = u.roleid");
        sql.append(" where username = ? and password = ? and status = 1");
        List<UserModel> userModels = query(sql.toString(), new UserMapper(), userModel.getUserName(),userModel.getPassword());
        return userModels.isEmpty() ? null : userModels.get(0);
    }
}
