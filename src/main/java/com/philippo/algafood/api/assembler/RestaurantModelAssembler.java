package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.controller.RestaurantController;
import com.philippo.algafood.api.model.RestaurantModel;
import com.philippo.algafood.core.security.AlgaSecurity;
import com.philippo.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestaurantModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public RestaurantModelAssembler() {
        super(RestaurantController.class, RestaurantModel.class);
    }

    @Override
    public RestaurantModel toModel(Restaurant restaurant) {
        RestaurantModel restaurantModel = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantModel);

        if(algaSecurity.canConsultRestaurants()) {
            restaurantModel.add(algaLinks.linkToRestaurants("restaurants"));
        }

        if (algaSecurity.canConsultKitchens()) {
            restaurantModel.add(algaLinks.linkToKitchen(restaurant.getKitchen().getId()));
        }

        if (algaSecurity.canConsultRestaurants()) {
            restaurantModel.add(algaLinks.linkToRestaurantPaymentMethods(restaurant.getId(), "payment-methods"));
        }

        if (algaSecurity.canManageRestaurantRegistration()) {
            restaurantModel.add(algaLinks.linkToRestaurantResponsibleUser(restaurant.getId(), "user-responsibles"));
        }


        if (restaurantModel.getAddress() != null && restaurantModel.getAddress().getCity() != null) {
            restaurantModel.add(algaLinks.linkToCity(restaurant.getAddress().getCity().getId()));
        }

        if (algaSecurity.canManageRestaurantRegistration()) {
            if (restaurant.activationAllowed()) {
                restaurantModel.add(algaLinks.linkToRestaurantActivation(restaurant.getId(), "activate"));
            }

            if (restaurant.activationAllowed()) {
                restaurantModel.add(algaLinks.linkToRestaurantInactivation(restaurant.getId(), "Inactivate"));
            }
        }

        if(algaSecurity.canManageRestaurantOperation(restaurant.getId())) {
            if (restaurant.openingAllowed()){
                restaurantModel.add(algaLinks.linkToRestaurantOpening(restaurant.getId(), "open"));
            }

            if (restaurant.closingAllowed()){
                restaurantModel.add(algaLinks.linkToRestaurantClosing(restaurant.getId(), "close"));
            }
        }

        return restaurantModel;
    }

    @Override
    public CollectionModel<RestaurantModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        CollectionModel<RestaurantModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.canConsultRestaurants()){
            collectionModel.add(algaLinks.linkToRestaurants());
        }

        return collectionModel;
    }
}
