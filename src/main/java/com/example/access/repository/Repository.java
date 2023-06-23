package com.example.access.repository;

import com.example.access.paging.Pageble;
import com.example.access.repository.entity.AbstractEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public interface Repository<E extends AbstractEntity, Id> {

    E findById(Id id);

    default void deleteById(Id id) {

    }

    default List<E> findBy(HashMap hashMap) {
        return null;
    }

    default String save(E entity) {
        return null;
    }

    default void deleteAll() {

    }

    default void update(E e) {
    }

    default List<E> findAll() {
        return Collections.emptyList();
    }

    default List<E> findAll(Pageble pageble) {
        return Collections.emptyList();
    }

}
