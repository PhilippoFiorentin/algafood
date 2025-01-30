package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.controller.GroupController;
import com.philippo.algafood.api.model.GroupModel;
import com.philippo.algafood.core.security.AlgaSecurity;
import com.philippo.algafood.domain.model.Group;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GroupModelAssembler extends RepresentationModelAssemblerSupport<Group, GroupModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public GroupModelAssembler() {
        super(GroupController.class, GroupModel.class);
    }

    @Override
    public GroupModel toModel(Group group) {
        GroupModel groupModel = createModelWithId(group.getId(), group);
        modelMapper.map(group, groupModel);

        if (algaSecurity.canConsultUsersGroupsPermissions()) {
            groupModel.add(algaLinks.linkToGroups("groups"));
            groupModel.add(algaLinks.linkToGroupPermissions(group.getId(), "permissions"));
        }

        return groupModel;
    }

    @Override
    public CollectionModel<GroupModel> toCollectionModel(Iterable<? extends Group> entities) {
        CollectionModel<GroupModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.canConsultUsersGroupsPermissions()) {
            collectionModel.add(algaLinks.linkToGroups());
        }

        return collectionModel;
    }
}
