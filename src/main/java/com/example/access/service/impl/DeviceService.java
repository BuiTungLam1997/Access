package com.example.access.service.impl;

import com.example.access.dao.IDeviceDAO;
import com.example.access.dao.ISiteDAO;
import com.example.access.dao.impl.DeviceDao;
import com.example.access.model.CloneDeviceModel;
import com.example.access.model.DeviceModel;
import com.example.access.model.SiteModel;
import com.example.access.paging.Pageble;
import com.example.access.service.ICloneDeviceService;
import com.example.access.service.IDeviceSevice;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class DeviceService implements IDeviceSevice {
    @Inject
    private IDeviceDAO deviceDAO;
    @Inject
    private ISiteDAO siteDAO;
    @Inject
    private ICloneDeviceService cloneDeviceServicel;

    @Override
    public List<DeviceModel> findById(String id) {
        return deviceDAO.findById(id);
    }

    @Override
    public DeviceModel save(DeviceModel deviceModel) {
        deviceModel.setCreateddate(new Timestamp(System.currentTimeMillis()));
        //lay id site
        SiteModel siteModel = siteDAO.findOneByCode(deviceModel.getSiteCode());
        deviceModel.setSiteid(siteModel.getId());
        //id auto increment
        String idMax = deviceDAO.getMaxId();
        String idString = "", idNumber = "";
        for (int i = 0; i < 3; i++) {
            idString += idMax.charAt(i);
        }
        for (int i = 3; i < idMax.length(); i++) {
            idNumber += idMax.charAt(i);
        }
        int idAdd = Integer.parseInt(idNumber);
        idAdd++;
        idNumber = String.valueOf(idAdd);
        deviceModel.setId(idString + idNumber);
        deviceModel.setVersion(1);
        String deviceId = deviceDAO.save(deviceModel);
        return deviceDAO.findOne(deviceId);
    }

    @Override
    public DeviceModel update(DeviceModel deviceModel) {
        DeviceModel oldDevice = deviceDAO.findOne(deviceModel.getId());
        //luu sang 1 db clone
        CloneDeviceModel clone = new CloneDeviceModel();
        clone.setId(oldDevice.getId());
        clone.setHistory(oldDevice.getHistory());
        clone.setInformation(oldDevice.getInformation());
        clone.setVersion(oldDevice.getVersion());
        clone.setUserName(oldDevice.getUserName());
        clone.setSiteid(oldDevice.getSiteid());
        clone.setCreatedby(oldDevice.getCreatedby());
        clone.setCreateddate(oldDevice.getCreateddate());
        clone.setModifiedby(oldDevice.getModifiedby());
        clone.setModifieddate(oldDevice.getModifieddate());
        cloneDeviceServicel.save(clone);
        //
        deviceModel.setCreateddate(oldDevice.getCreateddate());
        deviceModel.setCreatedby(oldDevice.getCreatedby());
        deviceModel.setModifieddate(new Timestamp(System.currentTimeMillis()));
        //lay id site
        SiteModel siteModel = siteDAO.findOneByCode(deviceModel.getSiteCode());
        deviceModel.setSiteid(siteModel.getId());
        //tang version
        Integer version = oldDevice.getVersion();
        version++;
        deviceModel.setVersion(version);
        //
        deviceDAO.update(deviceModel);
        return deviceDAO.findOne(deviceModel.getId());
    }

    @Override
    public void delete(String[] ids) {
        for (String id : ids) {
            DeviceModel oldDevice = deviceDAO.findOne(id);
            oldDevice.setModifieddate(new Timestamp(System.currentTimeMillis()));
            deviceDAO.delete(oldDevice);
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
        DeviceModel deviceModel = deviceDAO.findOne(id);
        SiteModel siteModel = siteDAO.findOne(deviceModel.getSiteid());
        deviceModel.setSiteCode(siteModel.getCode());
        return deviceModel;
    }
}
