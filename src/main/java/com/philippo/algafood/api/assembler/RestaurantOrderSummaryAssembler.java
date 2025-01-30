package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.controller.RestaurantOrderController;
import com.philippo.algafood.api.model.RestaurantOrderSummaryModel;
import com.philippo.algafood.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    public RestaurantOrderSummaryAssembler() {
        super(RestaurantOrderController.class, RestaurantOrderSummaryModel.class);
    }

    @Override
    public RestaurantOrderSummaryModel toModel(RestaurantOrder restaurantOrder) {
        RestaurantOrderSummaryModel restaurantOrderSummaryModel = createModelWithId(restaurantOrder.getId(), restaurantOrder);
        modelMapper.map(restaurantOrder, restaurantOrderSummaryModel);

        if (algaSecurity.canSearchOrders()) {
            restaurantOrderSummaryModel.add(algaLinks.linkToOrders("orders"));
        }

        if (algaSecurity.canConsultRestaurants()) {
            restaurantOrderSummaryModel.getRestaurant().add(algaLinks.linkToRestaurant(restaurantOrder.getRestaurant().getId()));
        }

        if (algaSecurity.canConsultUsersGroupsPermissions()) {
            restaurantOrderSummaryModel.getClient().add(algaLinks.linkToUser(restaurantOrder.getClient().getId()));
        }

        return restaurantOrderSummaryModel;
    }
}
