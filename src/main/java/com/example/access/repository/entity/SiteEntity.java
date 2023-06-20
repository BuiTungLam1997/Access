package com.example.access.repository.entity;

import com.example.access.annatation.Column;

public class SiteEntity extends AbstractEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
