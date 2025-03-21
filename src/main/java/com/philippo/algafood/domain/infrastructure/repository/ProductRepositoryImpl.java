package com.philippo.algafood.domain.infrastructure.repository;

import com.philippo.algafood.domain.model.ProductPhoto;
import com.philippo.algafood.domain.repository.ProductRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public ProductPhoto save(ProductPhoto photo) {
        return manager.merge(photo);
    }

    @Transactional
    @Override
    public void delete(ProductPhoto photo) {
        manager.remove(photo);
    }
}
