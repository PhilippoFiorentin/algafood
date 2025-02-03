package com.philippo.algafood.api.V1.controller;

import com.philippo.algafood.api.V1.AlgaLinks;
import com.philippo.algafood.api.V1.assembler.UserModelAssembler;
import com.philippo.algafood.api.V1.model.UserModel;
import com.philippo.algafood.api.V1.openapi.controller.RestaurantResponsibleUserControllerOpenApi;
import com.philippo.algafood.core.security.AlgaSecurity;
import com.philippo.algafood.core.security.CheckSecurity;
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

    @Autowired
    private AlgaSecurity algaSecurity;

    @CheckSecurity.Restaurants.CanConsult
    @GetMapping
    public CollectionModel<UserModel> list(@PathVariable Long restaurantId){
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);
        CollectionModel<UserModel> userModels = userModelAssembler.toCollectionModel(restaurant.getResponsibleUsers()).removeLinks();

        userModels.add(algaLinks.linkToRestaurantResponsibleUser(restaurantId));

        if (algaSecurity.canManageRestaurantRegistration()) {
            userModels.add(algaLinks.linkToAffiliateRestaurantResponsibleUser(restaurantId, "affiliate"));
        }

        userModels.getContent().stream().forEach(userModel -> {
            userModel.add(algaLinks.linkToDisaffiliateRestaurantResponsibleUser(
                    restaurantId, userModel.getId(), "disaffiliate"));
        });

        return userModels;
    }

    @CheckSecurity.Restaurants.CanManageRegister
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disaffiliate(@PathVariable Long restaurantId, @PathVariable Long userId){
        restaurantService.disaffiliateResponsibleUser(restaurantId, userId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.CanManageRegister
    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> affiliate(@PathVariable Long restaurantId, @PathVariable Long userId){
        restaurantService.affiliateResponsibleUser(restaurantId, userId);

        return ResponseEntity.noContent().build();
    }
}
