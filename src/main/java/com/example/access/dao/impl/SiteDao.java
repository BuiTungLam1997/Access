package com.example.access.dao.impl;

import com.example.access.dao.ISiteDAO;
import com.example.access.mapper.SiteMapper;
import com.example.access.model.SiteModel;

import java.util.List;

public class SiteDao extends AbstractDAO implements ISiteDAO {
    public List<SiteModel> findAll() {
        String sql = "SELECT * FROM site";
       return query(sql,new SiteMapper());
    }
}
