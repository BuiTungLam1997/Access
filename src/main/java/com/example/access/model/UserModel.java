package com.example.access.model;

import com.example.access.constant.SystemConstant;

public class UserModel extends AbstractModel<UserModel> {
    private String userName;
    private String password;
    private String fullName;
    private String status;
    private RoleModel role = new RoleModel();

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private int roleId;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isUser() {
        return getRole().getCode().equals(SystemConstant.USER);
    }

    public boolean isAdmin() {
        return getRole().getCode().equals(SystemConstant.ADMIN);
    }
}
