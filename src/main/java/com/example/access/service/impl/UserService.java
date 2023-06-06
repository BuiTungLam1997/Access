package com.example.access.service.impl;

import com.example.access.dao.IUserDao;
import com.example.access.model.UserModel;
import com.example.access.service.IUserSevice;

import javax.inject.Inject;

public class UserService implements IUserSevice {

    @Inject
    private IUserDao userDao;
    @Override
    public UserModel findCheckUser(UserModel userModel) {
        return userDao.findCheckUser(userModel);
    }
}
