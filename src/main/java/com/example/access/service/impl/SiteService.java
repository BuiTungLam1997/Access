package com.example.access.service.impl;

import com.example.access.dao.ISiteDAO;
import com.example.access.dao.impl.SiteDao;
import com.example.access.model.SiteModel;
import com.example.access.service.ISiteSevice;

import java.util.List;

public class SiteService implements ISiteSevice {
    private ISiteDAO siteDAO;
    public SiteService(){
        siteDAO = new SiteDao();
    }
    @Override
    public List<SiteModel> findAll() {
        return siteDAO.findAll();
    }

    @Override
    public SiteModel findOne(String siteid) {
        return siteDAO.findOne(siteid);
    }
}
