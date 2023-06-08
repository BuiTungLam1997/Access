package com.example.access.dao;

import com.example.access.model.SiteModel;

import java.util.List;

public interface ISiteDAO extends GenericDao{
    List<SiteModel> findAll();
    SiteModel findOne(String siteid);
    SiteModel findOneByCode(String code);
}
