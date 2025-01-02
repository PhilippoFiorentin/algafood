package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.controller.RestaurantController;
import com.philippo.algafood.api.controller.RestaurantOrderController;
import com.philippo.algafood.api.controller.UserController;
import com.philippo.algafood.api.model.RestaurantOrderSummaryModel;
import com.philippo.algafood.domain.model.RestaurantOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestaurantOrderSummaryAssembler extends RepresentationModelAssemblerSupport<RestaurantOrder, RestaurantOrderSummaryModel> {

    @Autowired
    private ModelMapper modelMapper;

    public RestaurantOrderSummaryAssembler() {
        super(RestaurantOrderController.class, RestaurantOrderSummaryModel.class);
    }

    @Override
    public RestaurantOrderSummaryModel toModel(RestaurantOrder restaurantOrder) {
        RestaurantOrderSummaryModel restaurantOrderSummaryModel = createModelWithId(restaurantOrder.getId(), restaurantOrder);
        modelMapper.map(restaurantOrder, restaurantOrderSummaryModel);

        restaurantOrderSummaryModel.add(WebMvcLinkBuilder.linkTo(RestaurantOrderController.class).withRel("orders"));
        restaurantOrderSummaryModel.getRestaurant().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(RestaurantController.class).find(restaurantOrder.getRestaurant().getId())).withSelfRel());
        restaurantOrderSummaryModel.getClient().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(UserController.class).find(restaurantOrder.getClient().getId())).withSelfRel());

        return restaurantOrderSummaryModel;
    }
}
