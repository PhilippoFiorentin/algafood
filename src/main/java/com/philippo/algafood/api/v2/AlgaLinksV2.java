package com.philippo.algafood.api.v2;

import com.philippo.algafood.api.v2.controller.CityControllerV2;
import com.philippo.algafood.api.v2.controller.KitchenControllerV2;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class AlgaLinksV2 {

    public Link linkToCities(String rel) {
        return WebMvcLinkBuilder.linkTo(CityControllerV2.class).withRel(rel);
    }

    public Link linkToCities() {
        return linkToCities(IanaLinkRelations.SELF.value());
    }

    public Link linkToKitchens(String rel) {
        return WebMvcLinkBuilder.linkTo(KitchenControllerV2.class).withRel(rel);
    }

    public Link linkToKitchens() {
        return linkToKitchens(IanaLinkRelations.SELF.value());
    }
}
