package com.example.access.repository.impl;

import com.example.access.repository.SiteRepository;
import com.example.access.repository.entity.SiteEntity;

import java.util.HashMap;
import java.util.List;

public class SiteRepositoryImpl extends SimpleJpaRepository<SiteEntity, String> implements SiteRepository {
    public SiteRepositoryImpl() {
        super(SiteEntity.class);
    }

    @Override
    public SiteEntity findByCode(String code) {
        HashMap hs = new HashMap();
        String field = "code";
        hs.put(field, code);
        List<SiteEntity> siteEntities = findBy(hs);
        return siteEntities.isEmpty() ? null : siteEntities.get(0);
    }
}
