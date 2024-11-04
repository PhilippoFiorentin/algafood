package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.GroupModelAssembler;
import com.philippo.algafood.api.model.GroupModel;
import com.philippo.algafood.domain.model.User;
import com.philippo.algafood.domain.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/groups")
public class UserGroupController {

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private GroupModelAssembler groupModelAssembler;

    @GetMapping
    public List<GroupModel> list(@PathVariable Long userId){
        User user  = registerUserService.findOrFail(userId);
        return groupModelAssembler.toCollectionModel(user.getGroups());
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disaffiliate(@PathVariable Long userId, @PathVariable Long groupId){
        registerUserService.disaffiliateGroup(userId, groupId);
    }

    @PutMapping("/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void affiliate(@PathVariable Long userId, @PathVariable Long groupId){
        registerUserService.affiliateGroup(userId, groupId);
    }
}
