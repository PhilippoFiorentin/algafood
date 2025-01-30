package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.assembler.PermissionModelAssembler;
import com.philippo.algafood.api.model.PermissionModel;
import com.philippo.algafood.api.openapi.controller.GroupPermissionControllerOpenApi;
import com.philippo.algafood.core.security.AlgaSecurity;
import com.philippo.algafood.core.security.CheckSecurity;
import com.philippo.algafood.domain.model.Group;
import com.philippo.algafood.domain.service.RegisterGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/groups/{groupId}/permissions", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupPermissionController implements GroupPermissionControllerOpenApi {

    @Autowired
    private RegisterGroupService registerGroupService;

    @Autowired
    private PermissionModelAssembler permissionModelAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    @CheckSecurity.UsersGroupsPermissions.CanConsult
    @GetMapping
    public CollectionModel<PermissionModel> list(@PathVariable Long groupId){
        Group group = registerGroupService.findOrFail(groupId);

        CollectionModel<PermissionModel> permissionModels = permissionModelAssembler
                .toCollectionModel(group.getPermissions()).removeLinks();

        permissionModels.add(algaLinks.linkToGroupPermissions(groupId));

        if (algaSecurity.canEditUsersGroupsPermissions()) {
            permissionModels.add(algaLinks.linkToAffiliateGroupPermission(groupId, "affiliate"));
        }

        permissionModels.getContent().forEach(permissionModel -> {
            permissionModel.add(algaLinks
                    .linkToDisaffiliateGroupPermission(groupId, permissionModel.getId(), "disaffiliate"));
        });

        return permissionModels;
    }

    @CheckSecurity.UsersGroupsPermissions.CanEdit
    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disaffiliate(@PathVariable Long groupId, @PathVariable Long permissionId){
        registerGroupService.disaffiliatePermission(groupId, permissionId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.UsersGroupsPermissions.CanEdit
    @PutMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> affiliate(@PathVariable Long groupId, @PathVariable Long permissionId){
        registerGroupService.affiliatePaymentMethod(groupId, permissionId);

        return ResponseEntity.noContent().build();
    }
}
