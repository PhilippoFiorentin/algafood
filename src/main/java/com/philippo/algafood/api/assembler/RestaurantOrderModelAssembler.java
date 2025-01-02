package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.controller.*;
import com.philippo.algafood.api.model.RestaurantOrderModel;
import com.philippo.algafood.domain.model.RestaurantOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestaurantOrderModelAssembler extends RepresentationModelAssemblerSupport<RestaurantOrder, RestaurantOrderModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestaurantOrderModelAssembler() {
        super(RestaurantOrderController.class, RestaurantOrderModel.class);
    }

    @Override
    public RestaurantOrderModel toModel(RestaurantOrder restaurantOrder) {
        RestaurantOrderModel restaurantOrderModel = createModelWithId(restaurantOrder.getId(), restaurantOrder);
        modelMapper.map(restaurantOrder, restaurantOrderModel);

        restaurantOrderModel.add(algaLinks.linkToOrders());

        restaurantOrderModel.getRestaurant().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(RestaurantController.class).find(restaurantOrder.getRestaurant().getId())).withSelfRel());
        restaurantOrderModel.getClient().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(UserController.class).find(restaurantOrder.getClient().getId())).withSelfRel());
        restaurantOrderModel.getPaymentMethod().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(PaymentMethodController.class).find(restaurantOrder.getPaymentMethod().getId(), null)).withSelfRel());
        restaurantOrderModel.getAddress().getCity().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(CityController.class).find(restaurantOrder.getDeliveryAddress().getCity().getId())).withSelfRel());

        restaurantOrderModel.getItems().forEach(item -> {
            item.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                    .methodOn(RestaurantProductController.class)
                    .find(restaurantOrderModel.getRestaurant().getId(), item.getId())).withRel("product"));
        });

        return restaurantOrderModel;
    }
}
