package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.controller.KitchenController;
import com.philippo.algafood.api.model.KitchenModel;
import com.philippo.algafood.core.security.AlgaSecurity;
import com.philippo.algafood.domain.model.Kitchen;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class KitchenModelAssembler extends RepresentationModelAssemblerSupport<Kitchen, KitchenModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public KitchenModelAssembler() {
        super(KitchenController.class, KitchenModel.class);
    }

    @Override
    public KitchenModel toModel(Kitchen kitchen){
        KitchenModel kitchenModel = createModelWithId(kitchen.getId(), kitchen);
        modelMapper.map(kitchen, kitchenModel);

        if (algaSecurity.canConsultKitchens()) {
            kitchenModel.add(algaLinks.linkToKitchens("kitchens"));
        }

        return kitchenModel;
    }

}
