package com.example.access.repository.impl;

import com.example.access.repository.SiteRepository;
import com.example.access.repository.entity.SiteEntity;

import java.util.List;

public class SiteRepositoryImpl extends SimpleJpaRepository<SiteEntity,String> implements SiteRepository  {
    public SiteRepositoryImpl(Class<SiteEntity> zclass) {
        super(zclass);
    }

    @Override
    public SiteEntity findByCode(String code) {
        String field = "code";
        List<SiteEntity> siteEntities = findBy(field,code);
        return siteEntities.isEmpty() ? null : siteEntities.get(0);
    }
}
