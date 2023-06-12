package com.example.access.dao;

import com.example.access.model.DeviceModel;
import com.example.access.paging.Pageble;

import java.util.List;

public interface IDeviceDAO extends GenericDao<DeviceModel> {
    List<DeviceModel> findById(String id);

    String save(DeviceModel deviceModel);

    DeviceModel findOne(String id);

    void update(DeviceModel updateDevice);

    void delete(DeviceModel deviceModel);

    List<DeviceModel> findAll(Pageble pageble);

    Integer getTotalItemDevice();

    String getMaxId();
}
