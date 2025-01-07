package com.philippo.algafood.api.v1.assembler;

import com.philippo.algafood.api.v1.AlgaLinks;
import com.philippo.algafood.api.v1.controller.RestaurantOrderController;
import com.philippo.algafood.api.v1.model.RestaurantOrderSummaryModel;
import com.philippo.algafood.domain.model.RestaurantOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestaurantOrderSummaryAssembler extends RepresentationModelAssemblerSupport<RestaurantOrder, RestaurantOrderSummaryModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestaurantOrderSummaryAssembler() {
        super(RestaurantOrderController.class, RestaurantOrderSummaryModel.class);
    }

    @Override
    public RestaurantOrderSummaryModel toModel(RestaurantOrder restaurantOrder) {
        RestaurantOrderSummaryModel restaurantOrderSummaryModel = createModelWithId(restaurantOrder.getId(), restaurantOrder);
        modelMapper.map(restaurantOrder, restaurantOrderSummaryModel);

        restaurantOrderSummaryModel.add(algaLinks.linkToOrders("orders"));

        restaurantOrderSummaryModel.getRestaurant().add(algaLinks.linkToRestaurant(restaurantOrder.getRestaurant().getId()));

        restaurantOrderSummaryModel.getClient().add(algaLinks.linkToUser(restaurantOrder.getClient().getId()));

        return restaurantOrderSummaryModel;
    }
}
