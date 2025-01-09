package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.controller.RestaurantController;
import com.philippo.algafood.api.model.BasicRestaurantModel;
import com.philippo.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestaurantBasicModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, BasicRestaurantModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;


    public RestaurantBasicModelAssembler() {
        super(RestaurantController.class, BasicRestaurantModel.class);
    }

    @Override
    public BasicRestaurantModel toModel(Restaurant restaurant) {
        BasicRestaurantModel restaurantBasicModel = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantBasicModel);

        restaurantBasicModel.add(algaLinks.linkToRestaurants("restaurants"));
        restaurantBasicModel.add(algaLinks.linkToKitchen(restaurantBasicModel.getKitchen().getId()));

        return restaurantBasicModel;
    }

    @Override
    public CollectionModel<BasicRestaurantModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        return super.toCollectionModel(entities).add(algaLinks.linkToRestaurants());
    }
}
