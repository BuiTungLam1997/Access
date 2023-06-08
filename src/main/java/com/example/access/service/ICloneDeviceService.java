package com.example.access.service;

import com.example.access.model.CloneDeviceModel;
import com.example.access.model.DeviceModel;
import com.example.access.paging.Pageble;

import java.util.List;

public interface ICloneDeviceService {
    List<CloneDeviceModel> findById(String id);
    CloneDeviceModel save(CloneDeviceModel cloneDeviceModel);
    CloneDeviceModel findOne(String id);
    List<DeviceModel> findAll(Pageble pageble);
}
