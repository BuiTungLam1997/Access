package com.example.access.service.impl;

import com.example.access.dao.IDeviceDAO;
import com.example.access.dao.ISiteDAO;
import com.example.access.model.CloneDeviceModel;
import com.example.access.model.DeviceModel;
import com.example.access.model.SiteModel;
import com.example.access.model.UserModel;
import com.example.access.paging.Pageble;
import com.example.access.service.ICloneDeviceService;
import com.example.access.service.IDeviceService;
import com.example.access.ultis.SessionUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

public class DeviceService implements IDeviceService {
    @Inject
    private IDeviceDAO deviceDAO;
    @Inject
    private ISiteDAO siteDAO;
    @Inject
    private ICloneDeviceService cloneDeviceService;

    @Override
    public List<DeviceModel> findById(String id) {
        return deviceDAO.findById(id);
    }

    @Override
    public DeviceModel save(DeviceModel deviceModel, HttpServletRequest request) {
        saveUtils(deviceModel, request);
        String deviceId = deviceDAO.save(deviceModel);
        return deviceDAO.findOne(deviceId);
    }

    private void saveUtils(DeviceModel deviceModel, HttpServletRequest request) {
        deviceModel.setCreateddate(new Timestamp(System.currentTimeMillis()));
        deviceModel.setSiteid(getIdSite(deviceModel));
        deviceModel.setId(getIdAuto());
        if (getFullName(request) != null) {
            deviceModel.setCreatedby(getFullName(request));
        } else deviceModel.setCreatedby("CALL FROM POSTMAN");
    }

    private String getIdSite(DeviceModel deviceModel) {
        if (deviceModel.getSiteCode() == null) {
            return null;
        }
        SiteModel siteModel = siteDAO.findOneByCode(deviceModel.getSiteCode());
        return siteModel.getId();
    }

    private String getIdAuto() {
        String idMax = deviceDAO.getMaxId();
        String idString = "";
        String idNumber = "";
        for (int i = 0; i < 3; i++) {
            idString += idMax.charAt(i);
        }
        for (int i = 3; i < idMax.length(); i++) {
            idNumber += idMax.charAt(i);
        }
        int idAdd = Integer.parseInt(idNumber);
        idAdd++;
        idNumber = String.valueOf(idAdd);
        return idString + idNumber;
    }

    @Override
    public DeviceModel update(DeviceModel deviceModel, HttpServletRequest request) {
        DeviceModel oldDevice = deviceDAO.findOne(deviceModel.getId());

        saveCloneDevice(oldDevice);

        updateUtils(deviceModel, oldDevice, request);

        deviceDAO.update(deviceModel);
        return deviceDAO.findOne(deviceModel.getId());
    }

    private void updateUtils(DeviceModel deviceModel, DeviceModel oldDevice, HttpServletRequest request) {
        deviceModel.setCreateddate(oldDevice.getCreateddate());
        deviceModel.setCreatedby(oldDevice.getCreatedby());
        deviceModel.setSiteid(getIdSite(deviceModel));

        deviceModel.setVersion(versionAuto(deviceModel));
        deviceModel.setModifieddate(new Timestamp(System.currentTimeMillis()));
        deviceModel.setModifiedby(getFullName(request));
        deviceModel.setVersion(versionAuto(oldDevice));
    }

    public Integer versionAuto(DeviceModel oldDevice) {
        Integer version = oldDevice.getVersion();
        return ++version;
    }

    private void saveCloneDevice(DeviceModel oldDevice) {
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

        cloneDeviceService.save(clone);
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

    private String getFullName(HttpServletRequest request) {
        UserModel userModel = (UserModel) SessionUtils.getInstance().getValue(request, "USERMODEL");
        if (userModel == null) {
            return null;
        }
        String fullName = userModel.getFullName();
        return fullName;
    }
}
