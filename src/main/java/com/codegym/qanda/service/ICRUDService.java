package com.codegym.qanda.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ICRUDService<T> {
    Page<T> findAll(Pageable pageable, String keyword);

    T findById(UUID id);

    T save(T t);

    void deleteById(UUID id);


    boolean existsById(UUID id);
}
