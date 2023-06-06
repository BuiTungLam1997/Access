package com.example.access.mapper;

import com.example.access.model.RoleModel;
import com.example.access.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserModel> {

    @Override
    public UserModel mapRow(ResultSet resultSet) {
        UserModel userModel = new UserModel();
        try {
            userModel.setId(resultSet.getString("id"));
            userModel.setUserName(resultSet.getString("username"));
            userModel.setPassword(resultSet.getString("password"));
            userModel.setFullName(resultSet.getString("fullName"));
            userModel.setStatus(resultSet.getString("status"));
            try {
                RoleModel roleModel = new RoleModel();
                roleModel.setCode(resultSet.getString("code"));
                roleModel.setName(resultSet.getString("name"));
                userModel.setRole(roleModel);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return userModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
