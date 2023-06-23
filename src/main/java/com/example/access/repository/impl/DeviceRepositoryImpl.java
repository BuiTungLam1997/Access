package com.example.access.repository.impl;

import com.example.access.annatation.Column;
import com.example.access.repository.DeviceRepository;
import com.example.access.repository.SiteRepository;
import com.example.access.repository.entity.DeviceEntity;
import com.example.access.repository.entity.SiteEntity;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;


public class DeviceRepositoryImpl extends SimpleJpaRepository<DeviceEntity, String> implements DeviceRepository {


    private SiteRepository siteRepository;
    @Inject
    private DeviceRepository deviceRepository;

    public DeviceRepositoryImpl() {
        super(DeviceEntity.class);
    }
    @Override
    public List<DeviceEntity> findBySiteCode(String siteCode) {
        SiteEntity site = siteRepository.findByCode(siteCode);
        String value = "";// site.getId(); lỗi supper class ,ko lấy được id từ supper class
        String field = "siteid";
        HashMap hs = new HashMap();
        hs.put(field, value);
        return findBy(hs);
    }

    @Override
    public List<DeviceEntity> findByAnything(DeviceEntity deviceEntity) {
        Column column = null;
        Object value = null;
        HashMap hs = new HashMap();
        try {
            Field[] fields = DeviceEntity.class.getDeclaredFields();
            for (Field field : fields) {
                column = field.getAnnotation(Column.class);
                if (column == null) {
                    continue;
                }
                field.setAccessible(true);
                value = field.get(deviceEntity);
                if (value != null) {
                    break;
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if (column != null && value != null) {
            hs.put(column.name(), value);
            return findBy(hs);
        }
        return null;
    }

    @Override
    public DeviceEntity saveDevice(DeviceEntity deviceEntity) {
        String id = deviceRepository.save(deviceEntity);
        return findById(id);
    }

}
