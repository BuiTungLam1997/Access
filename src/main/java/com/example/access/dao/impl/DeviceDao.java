package com.example.access.dao.impl;

import com.example.access.dao.IDeviceDAO;
import com.example.access.dao.ISiteDAO;
import com.example.access.mapper.DeviceMapper;
import com.example.access.model.DeviceModel;
import com.example.access.paging.Pageble;

import javax.inject.Inject;
import java.util.List;

public class DeviceDao extends AbstractDAO<DeviceModel> implements IDeviceDAO {
    @Inject
    private ISiteDAO siteDAO;
    @Override
    public List<DeviceModel> findById(String id) {
        String sql = "SELECT * FROM device WHERE id = ?";
        return query(sql, new DeviceMapper(), id);
    }

    @Override
    public String save(DeviceModel deviceModel) {
        String sql = " INSERT INTO device (id, information, siteid, username ,history,createddate ,createdby,isDelete,version) VALUES (?,?,?,?,?,?,?,?)";
        return insert(sql, deviceModel.getId(), deviceModel.getId(), deviceModel.getInformation(), deviceModel.getSiteid(),
                deviceModel.getUserName(), deviceModel.getHistory(), deviceModel.getCreateddate(),
                deviceModel.getCreatedby(), deviceModel.getIsDelete(),deviceModel.getVersion());
    }

    @Override
    public DeviceModel findOne(String id) {
        String sql = "SELECT * FROM device WHERE id = ?";
        List<DeviceModel> deviceModels = query(sql, new DeviceMapper(), id);
        return deviceModels.isEmpty() ? null : deviceModels.get(0);
    }

    @Override
    public void update(DeviceModel updateDevice) {
        StringBuilder sql = new StringBuilder("UPDATE device ");
        sql.append(" SET information = ? , history = ? , siteid = ? ,");
        sql.append(" username = ? ,version = ? ,createddate = ? ,createdby = ? ,");
        sql.append(" modifieddate = ? ,modifiedby = ?");
        sql.append(" WHERE id = ?");
        update(sql.toString(), updateDevice.getInformation(), updateDevice.getHistory(),
                updateDevice.getSiteid(), updateDevice.getUserName(),updateDevice.getVersion(), updateDevice.getCreateddate()
                , updateDevice.getCreatedby(), updateDevice.getModifieddate(), updateDevice.getModifiedby()
                , updateDevice.getId());
    }

    @Override
    public void delete(DeviceModel deviceModel) {
        StringBuilder sql = new StringBuilder("UPDATE device ");
        sql.append(" SET isDelete = 1 ");
        sql.append(" WHERE id = ?");
        update(sql.toString(), deviceModel.getId());
    }

    @Override
    public List<DeviceModel> findAll(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT * FROM device ");
        sql.append(" WHERE isDelete = 0 ");
//        if (pageble.getSorter()!=null) {
//            sql.append(" ODER BY ");
//            sql.append(pageble.getSorter().getSortName());
//            sql.append(" , ");
//            sql.append(pageble.getSorter().getSortBy());
//        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" LIMIT ");
            sql.append(pageble.getOffset());
            sql.append(",");
            sql.append(pageble.getLimit());
        }
        return query(sql.toString(), new DeviceMapper());
    }

    @Override
    public Integer getTotalItemDevice() {
        String sql = "SELECT count(*) FROM device WHERE isDelete = 0";
        return count(sql);
    }

    @Override
    public String getMaxId() {
        String sql = "SELECT MAX(id) FROM device";
        return maxId(sql);
    }
}
