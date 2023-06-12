package com.example.access.service;

import com.example.access.model.DeviceModel;
import com.example.access.paging.Pageble;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IDeviceService {
    List<DeviceModel> findById(String id);

    DeviceModel save(DeviceModel deviceModel, HttpServletRequest request);

    DeviceModel update(DeviceModel deviceModel, HttpServletRequest request);

    void delete(String[] ids);

    List<DeviceModel> findAll(Pageble pageble);

    int getTotalItemDevice();

    DeviceModel findOne(String id);
}
