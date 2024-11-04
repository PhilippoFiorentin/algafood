package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.PaymentMethodNotFoundException;
import com.philippo.algafood.domain.model.Permission;
import com.philippo.algafood.domain.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
