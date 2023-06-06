package com.example.access.dao;

import com.example.access.model.UserModel;

public interface IUserDao extends  GenericDao<UserModel>{
    UserModel findCheckUser(UserModel userModel);
}
