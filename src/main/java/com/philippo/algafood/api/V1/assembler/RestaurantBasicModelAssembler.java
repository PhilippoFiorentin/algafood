package com.philippo.algafood.api.V1.assembler;

import com.philippo.algafood.api.V1.AlgaLinks;
import com.philippo.algafood.api.V1.controller.RestaurantController;
import com.philippo.algafood.api.V1.model.BasicRestaurantModel;
import com.philippo.algafood.core.security.AlgaSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;


    public RestaurantBasicModelAssembler() {
        super(RestaurantController.class, BasicRestaurantModel.class);
    }

    @Override
    public BasicRestaurantModel toModel(Restaurant restaurant) {
        BasicRestaurantModel restaurantBasicModel = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantBasicModel);

        if (algaSecurity.canConsultRestaurants()){
            restaurantBasicModel.add(algaLinks.linkToRestaurants("restaurants"));
        }

        if (algaSecurity.canConsultKitchens()) {
            restaurantBasicModel.add(algaLinks.linkToKitchen(restaurantBasicModel.getKitchen().getId()));
        }

        return restaurantBasicModel;
    }

    @Override
    public CollectionModel<BasicRestaurantModel> toCollectionModel(Iterable<? extends Restaurant> entities) {
        CollectionModel<BasicRestaurantModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.canConsultRestaurants()) {
            collectionModel.add(algaLinks.linkToRestaurants());
        }

        return collectionModel;
    }
}
