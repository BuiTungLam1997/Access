package com.example.access.service.impl;

import com.example.access.dao.IDeviceDAO;
import com.example.access.dao.impl.DeviceDao;
import com.example.access.model.DeviceModel;
import com.example.access.model.SiteModel;
import com.example.access.paging.Pageble;
import com.example.access.service.IDeviceSevice;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class DeviceService implements IDeviceSevice {
    @Inject
    private IDeviceDAO deviceDAO;

    @Override
    public List<DeviceModel> findById(String id) {
        return deviceDAO.findById(id);
    }

    @Override
    public DeviceModel save(DeviceModel deviceModel) {
        deviceModel.setCreateddate(new Timestamp(System.currentTimeMillis()));
        String idMax = deviceDAO.getMaxId();
        String idString ="",idNumber="";
        for (int i = 0 ;i<3;i++){
            idString +=idMax.charAt(i);
        }
        for(int i = 3 ;i<idMax.length();i++){
            idNumber +=idMax.charAt(i);
        }
        int idAdd = Integer.parseInt(idNumber);
        idAdd++;
        idNumber = String.valueOf(idAdd);
        deviceModel.setId(idString+idNumber);
        deviceModel.setIsDelete(0);
        String deviceId = deviceDAO.save(deviceModel);
        return deviceDAO.findOne(deviceId);
    }

    @Override
    public DeviceModel update(DeviceModel deviceModel) {
        DeviceModel oldDevice = deviceDAO.findOne(deviceModel.getId());
        //luu sang 1 db clone
        deviceModel.setCreateddate(oldDevice.getCreateddate());
        deviceModel.setCreatedby(oldDevice.getCreatedby());
        deviceModel.setModifieddate(new Timestamp(System.currentTimeMillis()));

        deviceDAO.update(deviceModel);
        return deviceDAO.findOne(deviceModel.getId());
    }

    @Override
    public void delete(String[] ids) {
        DeviceModel deviceModel = new DeviceModel();
        for (String id : ids) {
            DeviceModel oldDevice = deviceDAO.findOne(id);
            deviceModel.setCreateddate(oldDevice.getCreateddate());
            deviceModel.setCreatedby(oldDevice.getCreatedby());
            deviceModel.setModifieddate(new Timestamp(System.currentTimeMillis()));
            deviceModel.setIsDelete(1);
            deviceDAO.update(deviceModel);
        }
    }

    @Override
    public List<DeviceModel> findAll(Pageble pageble) {
        return deviceDAO.findAll(pageble);
    }

    @Override
    public int getTotalItemDevice() {
        return deviceDAO.getTotalItemDevice();
    }

    @Override
    public DeviceModel findOne(String id) {
        return deviceDAO.findOne(id);
    }
}
