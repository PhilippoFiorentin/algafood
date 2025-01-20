package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.UserInputDisassembler;
import com.philippo.algafood.api.assembler.UserModelAssembler;
import com.philippo.algafood.api.model.UserModel;
import com.philippo.algafood.api.model.input.PasswordInput;
import com.philippo.algafood.api.model.input.UserInput;
import com.philippo.algafood.api.model.input.UserWithPasswordInput;
import com.philippo.algafood.api.openapi.controller.UserControllerOpenApi;
import com.philippo.algafood.domain.model.User;
import com.philippo.algafood.domain.repository.UserRepository;
import com.philippo.algafood.domain.service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements UserControllerOpenApi {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterUserService registerUser;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @Autowired
    private UserInputDisassembler userInputDisassembler;

    @GetMapping
    public CollectionModel<UserModel> list(){
        return userModelAssembler.toCollectionModel(userRepository.findAll());
    }

    @GetMapping("/{userId}")
    public UserModel find(@PathVariable Long userId){
        User user = registerUser.findOrFail(userId);

        return userModelAssembler.toModel(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel add(@RequestBody @Valid UserWithPasswordInput userInput){
        User user = userInputDisassembler.toDomainObject(userInput);
        return userModelAssembler.toModel(registerUser.save(user));
    }

    @PutMapping("/{userId}")
    public UserModel update(@PathVariable Long userId, @RequestBody @Valid UserInput userInput){
        User currentUser = registerUser.findOrFail(userId);
        userInputDisassembler.copyToDomainObject(userInput, currentUser);

        currentUser = registerUser.save(currentUser);

        return userModelAssembler.toModel(currentUser);
    }

    @PutMapping("/{userId}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@PathVariable Long userId, @RequestBody @Valid PasswordInput passwordInput){

        registerUser.changePassword(userId, passwordInput.getCurrentPassword(), passwordInput.getNewPassword());
    }
}
