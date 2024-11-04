package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.PermissionModelAssembler;
import com.philippo.algafood.api.model.PermissionModel;
import com.philippo.algafood.domain.model.Group;
import com.philippo.algafood.domain.service.RegisterGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups/{groupId}/permissions")
public class GroupPermissionController {

    @Autowired
    private RegisterGroupService registerGroupService;

    @Autowired
    private PermissionModelAssembler permissionModelAssembler;

    @GetMapping
    public List<PermissionModel> list(@PathVariable Long groupId){
        Group group = registerGroupService.findOrFail(groupId);
        return permissionModelAssembler.toCollectionModel(group.getPermissions());
    }

    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disaffiliate(@PathVariable Long groupId, @PathVariable Long permissionId){
        registerGroupService.disaffiliatePermission(groupId, permissionId);
    }

    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void affiliate(@PathVariable Long groupId, @PathVariable Long permissionId){
        registerGroupService.affiliatePaymentMethod(groupId, permissionId);
    }

}
