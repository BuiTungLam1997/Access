package com.example.access.service;

import com.example.access.model.DeviceModel;
import com.example.access.model.SiteModel;
import com.example.access.paging.Pageble;

import java.util.List;

public interface IDeviceSevice {
    List<DeviceModel> findById(String id);
    DeviceModel save(DeviceModel deviceModel);
    DeviceModel update(DeviceModel deviceModel);
    void delete (String[] ids);
    List<DeviceModel> findAll(Pageble pageble);
    int getTotalItemDevice();
    DeviceModel findOne(String id);
}
