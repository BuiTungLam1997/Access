package com.example.access.repository.entity;

import com.example.access.annatation.Column;

import java.sql.Timestamp;

public abstract class AbstractEntity {

    @Column(name = "createdby")
    private String createdby;
    @Column(name = "createddate")
    private Timestamp createddate;
    @Column(name = "createdby")
    private String modifiedby;
    @Column(name = "createddate")
    private Timestamp modifieddate;


    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }

    public String getModifiedby() {
        return modifiedby;
    }

    public void setModifiedby(String modifiedby) {
        this.modifiedby = modifiedby;
    }

    public Timestamp getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }
}
