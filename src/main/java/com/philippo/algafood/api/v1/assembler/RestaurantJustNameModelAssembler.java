package com.philippo.algafood.api.v1.assembler;

import com.philippo.algafood.api.v1.AlgaLinks;
import com.philippo.algafood.api.v1.controller.RestaurantController;
import com.philippo.algafood.api.v1.model.RestaurantJustNameModel;
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


    public RestaurantJustNameModelAssembler() {
        super(RestaurantController.class, RestaurantJustNameModel.class);
    }

    @Override
    public RestaurantJustNameModel toModel(Restaurant restaurant) {
        RestaurantJustNameModel restaurantJustNameModel = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantJustNameModel);

        restaurantJustNameModel.add(algaLinks.linkToRestaurants("restaurants"));

        return restaurantJustNameModel;
    }

    @Override
    public CollectionModel<RestaurantJustNameModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToRestaurants());
    }
}
