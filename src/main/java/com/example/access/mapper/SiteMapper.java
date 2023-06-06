package com.example.access.mapper;

import com.example.access.model.SiteModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SiteMapper implements RowMapper<SiteModel> {
    @Override
    public SiteModel mapRow(ResultSet resultSet) {
        SiteModel siteModel = new SiteModel();
        try {
            siteModel.setId(resultSet.getString("id"));
            siteModel.setName(resultSet.getString("name"));
            siteModel.setCode(resultSet.getString("code"));
            return siteModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
