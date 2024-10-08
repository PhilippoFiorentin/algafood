package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.exception.PaymentMethodNotFoundException;
import com.philippo.algafood.domain.exception.PermissionNotFoundException;
import com.philippo.algafood.domain.model.PaymentMethod;
import com.philippo.algafood.domain.model.Permission;
import com.philippo.algafood.domain.repository.PaymentMethodRepository;
import com.philippo.algafood.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission findOrFail(Long permissionId){
        return permissionRepository
                .findById(permissionId)
                .orElseThrow(() -> new PaymentMethodNotFoundException(permissionId));
    }
}
