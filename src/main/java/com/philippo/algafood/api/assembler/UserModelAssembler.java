package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.controller.*;
import com.philippo.algafood.api.model.RestaurantModel;
import com.philippo.algafood.api.model.UserModel;
import com.philippo.algafood.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

    @Autowired
    private ModelMapper modelMapper;

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(User user){
        UserModel userModel = createModelWithId(user.getId(), user);

        modelMapper.map(user, userModel);

        userModel.add(WebMvcLinkBuilder.linkTo((UserController.class)).withRel("users"));

        userModel.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UserGroupController.class).list(user.getId())).withRel("user-groups"));

        return userModel;
    }

    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities) {
        return super.toCollectionModel(entities).add(WebMvcLinkBuilder.linkTo(UserController.class).withSelfRel());
    }
}
