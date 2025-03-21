package com.philippo.algafood.api.V1.controller;

import com.philippo.algafood.api.V1.assembler.UserInputDisassembler;
import com.philippo.algafood.api.V1.assembler.UserModelAssembler;
import com.philippo.algafood.api.V1.model.UserModel;
import com.philippo.algafood.api.V1.model.input.PasswordInput;
import com.philippo.algafood.api.V1.model.input.UserInput;
import com.philippo.algafood.api.V1.model.input.UserWithPasswordInput;
import com.philippo.algafood.api.V1.openapi.controller.UserControllerOpenApi;
import com.philippo.algafood.core.security.CheckSecurity;
import com.philippo.algafood.domain.model.User;
import com.philippo.algafood.domain.repository.UserRepository;
import com.philippo.algafood.domain.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements UserControllerOpenApi {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterUserService registerUser;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @Autowired
    private UserInputDisassembler userInputDisassembler;

    @CheckSecurity.UsersGroupsPermissions.CanConsult
    @GetMapping
    public CollectionModel<UserModel> list(){
        return userModelAssembler.toCollectionModel(userRepository.findAll());
    }

    @CheckSecurity.UsersGroupsPermissions.CanConsult
    @GetMapping("/{userId}")
    public UserModel find(@PathVariable Long userId){
        User user = registerUser.findOrFail(userId);

        return userModelAssembler.toModel(user);
    }

    @CheckSecurity.UsersGroupsPermissions.CanEdit
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel add(@RequestBody @Valid UserWithPasswordInput userInput){
        User user = userInputDisassembler.toDomainObject(userInput);
        return userModelAssembler.toModel(registerUser.save(user));
    }

    @CheckSecurity.UsersGroupsPermissions.CanChangeUser
    @PutMapping("/{userId}")
    public UserModel update(@PathVariable Long userId, @RequestBody @Valid UserInput userInput){
        User currentUser = registerUser.findOrFail(userId);
        userInputDisassembler.copyToDomainObject(userInput, currentUser);

        currentUser = registerUser.save(currentUser);

        return userModelAssembler.toModel(currentUser);
    }

    @CheckSecurity.UsersGroupsPermissions.CanChangePassword
    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable Long userId, @RequestBody @Valid PasswordInput passwordInput){

        registerUser.changePassword(userId, passwordInput.getCurrentPassword(), passwordInput.getNewPassword());
    }
}
