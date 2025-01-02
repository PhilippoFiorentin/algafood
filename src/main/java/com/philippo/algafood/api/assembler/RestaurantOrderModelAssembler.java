package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.controller.RestaurantOrderController;
import com.philippo.algafood.api.model.RestaurantOrderModel;
import com.philippo.algafood.domain.model.RestaurantOrder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
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

        restaurantOrderModel.getRestaurant().add(algaLinks.linkToRestaurant(restaurantOrder.getRestaurant().getId()));

        restaurantOrderModel.getClient().add(algaLinks.linkToUser(restaurantOrder.getClient().getId()));

        restaurantOrderModel.getPaymentMethod().add(algaLinks.linkToPaymentMethod(restaurantOrder.getPaymentMethod().getId()));

        restaurantOrderModel.getAddress().getCity().add(algaLinks.linkToCity(restaurantOrder.getDeliveryAddress().getCity().getId()));

        restaurantOrderModel.getItems().forEach(item -> {
            item.add(algaLinks.linkToProduct(restaurantOrderModel.getRestaurant().getId(), item.getId(), "product"));
        });

        return restaurantOrderModel;
    }
}
