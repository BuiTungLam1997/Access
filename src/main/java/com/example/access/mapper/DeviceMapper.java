package com.example.access.mapper;

import com.example.access.model.DeviceModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceMapper implements RowMapper<DeviceModel> {
    @Override
    public DeviceModel mapRow(ResultSet resultSet) {
        try {
            DeviceModel deviceModel = new DeviceModel();
            deviceModel.setId(resultSet.getString("id"));
            deviceModel.setInformation(resultSet.getString("information"));
            deviceModel.setHistory(resultSet.getString("history"));
            deviceModel.setSiteid(resultSet.getString("siteid"));
            deviceModel.setUserName(resultSet.getString("username"));
            deviceModel.setCreatedby(resultSet.getString("createdby"));
            deviceModel.setCreateddate(resultSet.getTimestamp("createddate"));
            deviceModel.setIsDelete(resultSet.getInt("isDelete"));
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
