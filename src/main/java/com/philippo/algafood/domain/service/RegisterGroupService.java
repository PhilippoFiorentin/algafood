package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.exception.GroupNotFoundException;
import com.philippo.algafood.domain.model.Group;
import com.philippo.algafood.domain.model.Permission;
import com.philippo.algafood.domain.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterGroupService {

    public static final String GROUP_IN_USE_MESSAGE = "The group with code %d could not be deleted because it is in use";

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private RegisterPermissionService registerPermissionService;

    @Transactional
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Transactional
    public void delete(Long groupId) {
        try {
            groupRepository.deleteById(groupId);
            groupRepository.flush();
        }catch (EmptyResultDataAccessException e){
            throw new GroupNotFoundException(groupId);
        }catch (DataIntegrityViolationException e){
            throw new EntityInUseException(String.format(GROUP_IN_USE_MESSAGE));
        }
    }

    @Transactional
    public void disaffiliatePermission(Long groupId, Long permissionId){
        Group group = findOrFail(groupId);
        Permission permission = registerPermissionService.findOrFail(permissionId);

        group.removePermission(permission);
    }

    @Transactional
    public void affiliatePaymentMethod(Long groupId, Long permissionId){
        Group group = findOrFail(groupId);
        Permission permission = registerPermissionService.findOrFail(permissionId);

        group.addPermission(permission);
    }

    public Group findOrFail(Long groupId){
        return groupRepository
                .findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(groupId));
    }
}
