package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.controller.RestaurantController;
import com.philippo.algafood.api.model.RestaurantJustNameModel;
import com.philippo.algafood.core.security.AlgaSecurity;
import com.philippo.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestaurantJustNameModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantJustNameModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;


    public RestaurantJustNameModelAssembler() {
        super(RestaurantController.class, RestaurantJustNameModel.class);
    }

    @Override
    public RestaurantJustNameModel toModel(Restaurant restaurant) {
        RestaurantJustNameModel restaurantJustNameModel = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantJustNameModel);

        if (algaSecurity.canConsultRestaurants()){
            restaurantJustNameModel.add(algaLinks.linkToRestaurants("restaurants"));
        }

        return restaurantJustNameModel;
    }

    @Override
    public CollectionModel<RestaurantJustNameModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        CollectionModel<RestaurantJustNameModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.canConsultRestaurants()){
            collectionModel.add(algaLinks.linkToRestaurants());
        }

        return collectionModel;
    }
}
