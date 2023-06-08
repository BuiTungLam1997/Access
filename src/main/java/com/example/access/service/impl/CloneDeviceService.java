package com.example.access.service.impl;

import com.example.access.dao.ICloneDeviceDAO;
import com.example.access.model.CloneDeviceModel;
import com.example.access.model.DeviceModel;
import com.example.access.paging.Pageble;
import com.example.access.service.ICloneDeviceService;

import javax.inject.Inject;
import java.util.List;

public class CloneDeviceService implements ICloneDeviceService {
    @Inject
    private ICloneDeviceDAO cloneDeviceDAO;
    @Override
    public List<CloneDeviceModel> findById(String id) {
        return cloneDeviceDAO.findById(id);
    }

    @Override
    public CloneDeviceModel save(CloneDeviceModel cloneDeviceModel) {
        String cloneId = cloneDeviceDAO.save(cloneDeviceModel);
        return cloneDeviceDAO.findOne(cloneId);
    }

    @Override
    public CloneDeviceModel findOne(String id) {
        return null;
    }

    @Override
    public List<DeviceModel> findAll(Pageble pageble) {
        return null;
    }
}
