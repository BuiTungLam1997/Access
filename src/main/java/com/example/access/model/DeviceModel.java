package com.example.access.model;

import com.example.access.constant.SystemConstant;

public class DeviceModel extends AbstractModel<DeviceModel> {

    private String information;
    private String history;
    private String siteid;
    private String userName;
    private String sortName;
    private String sortBy;
    private Integer isDelete;
    private Integer version = 1;
    private String siteCode;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public boolean isList() {
        return getType().equals(SystemConstant.LIST);
    }

    public boolean isEdit() {
        return getType().equals(SystemConstant.EDIT);
    }
}
