package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.UserModelAssembler;
import com.philippo.algafood.api.model.UserModel;
import com.philippo.algafood.api.openapi.controller.RestaurantUserResponsibleControllerOpenApi;
import com.philippo.algafood.domain.model.Restaurant;
import com.philippo.algafood.domain.service.RegisterRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/responsible", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUserResponsibleController implements RestaurantUserResponsibleControllerOpenApi {

    @Autowired
    private RegisterRestaurantService restaurantService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @GetMapping
    public CollectionModel<UserModel> list(@PathVariable Long restaurantId){
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);
        return userModelAssembler
                .toCollectionModel(restaurant.getUsersResponsible())
                .removeLinks()
                .add(WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(RestaurantUserResponsibleController.class).list(restaurantId))
                        .withSelfRel());
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disaffiliate(@PathVariable Long restaurantId, @PathVariable Long userId){
        restaurantService.disaffiliateUserResponsible(restaurantId, userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void affiliate(@PathVariable Long restaurantId, @PathVariable Long userId){
        restaurantService.affiliateUserResponsible(restaurantId, userId);
    }
}
