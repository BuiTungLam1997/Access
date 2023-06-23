package com.example.access.repository.entity;

import com.example.access.annatation.Column;
import com.example.access.annatation.Id;
import com.example.access.annatation.Table;

@Table(name = "device")
public class DeviceEntity extends AbstractEntity {
    @Id()
    @Column(name = "id")
    private String id;
    @Column(name = "information")
    private String information;
    @Column(name = "history")
    private String history;
    @Column(name = "siteid")
    private String siteid;
    @Column(name = "username")
    private String userName;
    @Column(name = "isDelete")
    private Integer isDelete = 0;
    @Column(name = "version")
    private Integer version = 1;
    private String siteCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

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
}
