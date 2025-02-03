package com.philippo.algafood.api.V1.assembler;

import com.philippo.algafood.api.V1.AlgaLinks;
import com.philippo.algafood.api.V1.controller.RestaurantOrderController;
import com.philippo.algafood.api.V1.model.RestaurantOrderModel;
import com.philippo.algafood.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    public RestaurantOrderModelAssembler() {
        super(RestaurantOrderController.class, RestaurantOrderModel.class);
    }

    @Override
    public RestaurantOrderModel toModel(RestaurantOrder restaurantOrder) {
        RestaurantOrderModel restaurantOrderModel = createModelWithId(restaurantOrder.getId(), restaurantOrder);
        modelMapper.map(restaurantOrder, restaurantOrderModel);

        if (algaSecurity.canSearchOrders()) {
            restaurantOrderModel.add(algaLinks.linkToOrders("orders"));
        }

        if (algaSecurity.canManageOrders(restaurantOrder.getUuid())){
            if (restaurantOrder.canBeConfirmed()){
                restaurantOrderModel.add(algaLinks.linkToOrderConfirmation(restaurantOrder.getUuid(), "confirm"));
            }

            if (restaurantOrder.canBeDelivered()){
                restaurantOrderModel.add(algaLinks.linkToOrderDelivery(restaurantOrder.getUuid(), "deliver"));
            }

            if (restaurantOrder.canBeCancelled()){
                restaurantOrderModel.add(algaLinks.linkToOrderCancellation(restaurantOrder.getUuid(), "cancel"));
            }
        }

        if(algaSecurity.canConsultRestaurants()) {
            restaurantOrderModel.getRestaurant().add(algaLinks.linkToRestaurant(restaurantOrder.getRestaurant().getId()));
        }

        if (algaSecurity.canConsultUsersGroupsPermissions()) {
            restaurantOrderModel.getClient().add(algaLinks.linkToUser(restaurantOrder.getClient().getId()));
        }

        if (algaSecurity.canConsultPaymentMethods()) {
            restaurantOrderModel.getPaymentMethod().add(algaLinks.linkToPaymentMethod(restaurantOrder.getPaymentMethod().getId()));
        }

        if (algaSecurity.canConsultCities()) {
            restaurantOrderModel.getAddress().getCity().add(algaLinks.linkToCity(restaurantOrder.getDeliveryAddress().getCity().getId()));
        }

        if(algaSecurity.canConsultRestaurants()) {
            restaurantOrderModel.getItems().forEach(item -> {
                item.add(algaLinks.linkToProduct(restaurantOrderModel.getRestaurant().getId(), item.getId(), "product"));
            });
        }

        return restaurantOrderModel;
    }
}
