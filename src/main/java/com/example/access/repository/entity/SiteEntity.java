package com.example.access.repository.entity;

import com.example.access.annatation.Column;
import com.example.access.annatation.Id;
import com.example.access.annatation.Table;

@Table(name = "site")
public class SiteEntity extends AbstractEntity {
    @Id()
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
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
