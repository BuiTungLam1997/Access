package com.example.access.repository;

import com.example.access.repository.entity.DeviceEntity;

import java.util.List;

public interface DeviceRepository extends Repository<DeviceEntity, String> {

    List<DeviceEntity> findBySiteCode(String siteCode);
    List<DeviceEntity> findByAnything(DeviceEntity deviceEntity);
    DeviceEntity saveDevice(DeviceEntity deviceEntity);

}
