package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.AlgaLinks;
import com.philippo.algafood.api.assembler.UserModelAssembler;
import com.philippo.algafood.api.model.UserModel;
import com.philippo.algafood.api.openapi.controller.RestaurantResponsibleUserControllerOpenApi;
import com.philippo.algafood.domain.model.Restaurant;
import com.philippo.algafood.domain.service.RegisterRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/restaurants/{restaurantId}/responsible", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantResponsibleUserController implements RestaurantResponsibleUserControllerOpenApi {

    @Autowired
    private RegisterRestaurantService restaurantService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public CollectionModel<UserModel> list(@PathVariable Long restaurantId){
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);
        CollectionModel<UserModel> userModels = userModelAssembler.toCollectionModel(restaurant.getResponsibleUsers()).removeLinks()
                .add(algaLinks.linkToRestaurantResponsibleUser(restaurantId))
                .add(algaLinks.linkToAffiliateRestaurantResponsibleUser(restaurantId, "affiliate"));

        userModels.getContent().stream().forEach(userModel -> {
            userModel.add(algaLinks.linkToDisaffiliateRestaurantResponsibleUser(
                    restaurantId, userModel.getId(), "disaffiliate"));
        });

        return userModels;
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disaffiliate(@PathVariable Long restaurantId, @PathVariable Long userId){
        restaurantService.disaffiliateResponsibleUser(restaurantId, userId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> affiliate(@PathVariable Long restaurantId, @PathVariable Long userId){
        restaurantService.affiliateResponsibleUser(restaurantId, userId);

        return ResponseEntity.noContent().build();
    }
}
