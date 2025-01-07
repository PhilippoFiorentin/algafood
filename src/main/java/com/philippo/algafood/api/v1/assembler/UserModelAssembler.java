package com.philippo.algafood.api.v1.assembler;

import com.philippo.algafood.api.v1.AlgaLinks;
import com.philippo.algafood.api.v1.model.UserModel;
import com.philippo.algafood.api.v1.controller.UserController;
import com.philippo.algafood.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(User user){
        UserModel userModel = createModelWithId(user.getId(), user);

        modelMapper.map(user, userModel);

        userModel.add(algaLinks.linkToUsers("users"));

        userModel.add(algaLinks.linkToUserGroups(user.getId(), "user-groups"));

        return userModel;
    }

    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToUsers());
    }
}
