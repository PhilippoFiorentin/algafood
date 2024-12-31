package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.controller.KitchenController;
import com.philippo.algafood.api.model.KitchenModel;
import com.philippo.algafood.domain.model.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KitchenModelAssembler extends RepresentationModelAssemblerSupport<Kitchen, KitchenModel> {

    @Autowired
    private ModelMapper modelMapper;

    public KitchenModelAssembler() {
        super(KitchenController.class, KitchenModel.class);
    }

    @Override
    public KitchenModel toModel(Kitchen kitchen){
        KitchenModel kitchenModel = createModelWithId(kitchen.getId(), kitchen);
        modelMapper.map(kitchen, kitchenModel);

        kitchenModel.add(WebMvcLinkBuilder.linkTo(KitchenController.class).withRel("kitchens"));
 
        return kitchenModel;
    }

}
