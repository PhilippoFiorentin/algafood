package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.UserModel;
import com.philippo.algafood.api.V1.model.input.PasswordInput;
import com.philippo.algafood.api.V1.model.input.UserInput;
import com.philippo.algafood.api.V1.model.input.UserWithPasswordInput;
import org.springframework.hateoas.CollectionModel;

public interface UserControllerOpenApi {

    CollectionModel<UserModel> list();

    UserModel find(Long userId);

    UserModel add(UserWithPasswordInput userInput);

    UserModel update(Long userId, UserInput userInput);

    void changePassword(Long userId, PasswordInput passwordInput);
}
