package com.example.access.repository;

import com.example.access.paging.Pageble;
import com.example.access.repository.entity.AbstractEntity;

import java.util.Collections;
import java.util.List;

public interface Repository<E extends AbstractEntity, Id> {

    E findById(Id id);

    default void deleteById(Id id) {

    }

    default List<E> findBy(String field, String value) {
        return null;
    }

    default E save(E entity) {
        return entity;
    }

    default void deleteAll() {

    }

    default E update(E e) {
        return e;
    }

    default List<E> findAll() {
        return Collections.emptyList();
    }

    default List<E> findAll(Pageble pageble) {
        return Collections.emptyList();
    }

}
