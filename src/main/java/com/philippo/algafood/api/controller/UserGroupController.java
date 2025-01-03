package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.assembler.GroupModelAssembler;
import com.philippo.algafood.api.model.GroupModel;
import com.philippo.algafood.api.openapi.controller.UserGroupControllerOpenApi;
import com.philippo.algafood.domain.model.User;
import com.philippo.algafood.domain.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users/{userId}/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserGroupController implements UserGroupControllerOpenApi {

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private GroupModelAssembler groupModelAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public CollectionModel<GroupModel> list(@PathVariable Long userId){
        User user  = registerUserService.findOrFail(userId);
        CollectionModel<GroupModel> groupModels = groupModelAssembler
                .toCollectionModel(user.getGroups())
                .removeLinks()
                .add(algaLinks.linkToAffiliateUserGroup(user.getId(), "affiliate"));

        groupModels.getContent().forEach(groupModel -> {
            groupModel.add(algaLinks.linkToDisaffiliateUserGroup(userId, groupModel.getId(), "disaffiliate"));
        });

        return groupModels;
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disaffiliate(@PathVariable Long userId, @PathVariable Long groupId){
        registerUserService.disaffiliateGroup(userId, groupId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> affiliate(@PathVariable Long userId, @PathVariable Long groupId){
        registerUserService.affiliateGroup(userId, groupId);

        return ResponseEntity.noContent().build();
    }
}
