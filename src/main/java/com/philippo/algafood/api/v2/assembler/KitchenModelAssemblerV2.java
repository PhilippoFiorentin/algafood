package com.philippo.algafood.api.v2.assembler;

import com.philippo.algafood.api.v2.AlgaLinksV2;
import com.philippo.algafood.api.v2.controller.KitchenControllerV2;
import com.philippo.algafood.api.v2.model.KitchenModelV2;
import com.philippo.algafood.domain.model.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class KitchenModelAssemblerV2 extends RepresentationModelAssemblerSupport<Kitchen, KitchenModelV2> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinksV2 algaLinks;

    public KitchenModelAssemblerV2() {
        super(KitchenControllerV2.class, KitchenModelV2.class);
    }

    @Override
    public KitchenModelV2 toModel(Kitchen kitchen){
        KitchenModelV2 kitchenModel = createModelWithId(kitchen.getId(), kitchen);
        modelMapper.map(kitchen, kitchenModel);

        kitchenModel.add(algaLinks.linkToKitchens("kitchens"));

        return kitchenModel;
    }

}
