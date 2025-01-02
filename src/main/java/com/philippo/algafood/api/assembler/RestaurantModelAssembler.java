package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.controller.RestaurantController;
import com.philippo.algafood.api.model.RestaurantModel;
import com.philippo.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public RestaurantModelAssembler() {
        super(RestaurantController.class, RestaurantModel.class);
    }

    @Override
    public RestaurantModel toModel(Restaurant restaurant) {
        RestaurantModel restaurantModel = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantModel);

        restaurantModel.add(algaLinks.linkToRestaurants("restaurants"));
        restaurantModel.add(algaLinks.linkToKitchen(restaurant.getKitchen().getId()));
        restaurantModel.add(algaLinks.linkToCity(restaurant.getAddress().getCity().getId()));
        restaurantModel.add(algaLinks.linkToRestaurantPaymentMethods(restaurant.getId(), "payment-methods"));
        restaurantModel.add(algaLinks.linkToRestaurantUserResponsibles(restaurant.getId(), "user-responsibles"));

        if (restaurant.activationAllowed()){
            restaurantModel.add(algaLinks.linkToRestaurantActivation(restaurant.getId(), "activate"));
        }

        if (restaurant.activationAllowed()){
            restaurantModel.add(algaLinks.linkToRestaurantInactivation(restaurant.getId(), "Inactivate"));
        }

        if (restaurant.openingAllowed()){
            restaurantModel.add(algaLinks.linkToRestaurantOpening(restaurant.getId(), "open"));
        }

        if (restaurant.closingAllowed()){
            restaurantModel.add(algaLinks.linkToRestaurantClosing(restaurant.getId(), "close"));
        }

        return restaurantModel;
    }

    @Override
    public CollectionModel<RestaurantModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToRestaurants());
    }
}
