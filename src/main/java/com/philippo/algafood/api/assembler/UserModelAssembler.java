package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.model.UserModel;
import com.philippo.algafood.api.controller.UserController;
import com.philippo.algafood.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(User user){
        UserModel userModel = createModelWithId(user.getId(), user);

        modelMapper.map(user, userModel);

        if(algaSecurity.canConsultUsersGroupsPermissions()) {
            userModel.add(algaLinks.linkToUsers("users"));

            userModel.add(algaLinks.linkToUserGroups(user.getId(), "user-groups"));
        }

        return userModel;
    }

    @Override
    public CollectionModel<UserModel> toCollectionModel(Iterable<? extends User> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToUsers());
    }
}
