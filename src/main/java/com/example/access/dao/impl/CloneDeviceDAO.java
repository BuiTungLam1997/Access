package com.example.access.dao.impl;

import com.example.access.dao.ICloneDeviceDAO;
import com.example.access.mapper.CloneDeviceMapper;
import com.example.access.mapper.DeviceMapper;
import com.example.access.model.CloneDeviceModel;
import com.example.access.paging.Pageble;

import java.util.List;

public class CloneDeviceDAO extends AbstractDAO<CloneDeviceModel> implements ICloneDeviceDAO {
    @Override
    public List<CloneDeviceModel> findById(String id) {
        String sql = "SELECT * FROM clonedevice WHERE id = ?";
        return query(sql, new CloneDeviceMapper(), id);
    }

    @Override
    public String save(CloneDeviceModel clone) {
        StringBuilder sql = new StringBuilder("INSERT INTO clonedevice ");
        sql.append(" (id,information,history ,siteid , username , createdby , createddate ,modifiedby,modifieddate ,version) ");
        sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?)");
        return insert(sql.toString(),clone.getId(),clone.getId(),clone.getInformation(),clone.getHistory(),clone.getSiteid()
                ,clone.getUserName(),clone.getCreatedby(),clone.getCreateddate(),clone.getModifiedby(),
                clone.getModifieddate(),clone.getVersion());
    }

    @Override
    public CloneDeviceModel findOne(String id) {
        String sql = "SELECT * FROM clonedevice WHERE id = ?";
        List<CloneDeviceModel> clone = query(sql, new CloneDeviceMapper(), id);
        return clone.isEmpty() ? null : clone.get(0);
    }

    @Override
    public List<CloneDeviceModel> findAll(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT * FROM clonedevice ");
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" LIMIT ");
            sql.append(pageble.getOffset());
            sql.append(",");
            sql.append(pageble.getLimit());
        }
        return query(sql.toString(), new CloneDeviceMapper());
    }
}
