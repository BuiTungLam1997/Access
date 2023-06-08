package com.example.access.dao;

import com.example.access.model.CloneDeviceModel;
import com.example.access.model.DeviceModel;
import com.example.access.paging.Pageble;

import java.util.List;

public interface ICloneDeviceDAO extends GenericDao<CloneDeviceModel>{
    List<CloneDeviceModel> findById(String id);
    String save(CloneDeviceModel cloneDeviceModel);
    CloneDeviceModel findOne(String id);
    List<CloneDeviceModel> findAll(Pageble pageble);
}
