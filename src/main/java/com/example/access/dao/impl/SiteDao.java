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

    @Override
    public SiteModel findOne(String siteid) {
        String sql = "SELECT * FROM site WHERE id = ?";
        List<SiteModel> siteModels = query(sql, new SiteMapper(), siteid);
        return siteModels.isEmpty() ? null : siteModels.get(0);
    }

    @Override
    public SiteModel findOneByCode(String code) {
        String sql = "SELECT * FROM site WHERE code = ?";
        List<SiteModel> siteModels = query(sql, new SiteMapper(), code);
        return siteModels.isEmpty() ? null : siteModels.get(0);
    }
}
