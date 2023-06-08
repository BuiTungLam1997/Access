package com.example.access.mapper;

import com.example.access.model.CloneDeviceModel;
import com.example.access.model.DeviceModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CloneDeviceMapper implements RowMapper<CloneDeviceModel> {

    @Override
    public CloneDeviceModel mapRow(ResultSet resultSet) {

        try {
            CloneDeviceModel deviceModel = new CloneDeviceModel();
            deviceModel.setId(resultSet.getString("id"));
            deviceModel.setInformation(resultSet.getString("information"));
            deviceModel.setHistory(resultSet.getString("history"));
            deviceModel.setSiteid(resultSet.getString("siteid"));
            deviceModel.setUserName(resultSet.getString("username"));
            deviceModel.setCreatedby(resultSet.getString("createdby"));
            deviceModel.setCreateddate(resultSet.getTimestamp("createddate"));
            deviceModel.setVersion(resultSet.getInt("version"));
            if (deviceModel.getModifieddate() != null) {
                deviceModel.setModifieddate(resultSet.getTimestamp("modifieddate"));
            }
            if (deviceModel.getModifiedby() != null) {
                deviceModel.setModifiedby(resultSet.getString("modifiedby"));
            }
            return deviceModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
