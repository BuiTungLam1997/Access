package com.example.access.repository;

import com.example.access.repository.entity.SiteEntity;

public interface SiteRepository extends Repository<SiteEntity,String>{
    SiteEntity findByCode(String code);
}
