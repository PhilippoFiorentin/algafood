package com.philippo.algafood.domain.repository;

import com.philippo.algafood.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
