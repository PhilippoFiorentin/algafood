package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.UserModelAssembler;
import com.philippo.algafood.api.model.UserModel;
import com.philippo.algafood.domain.model.Restaurant;
import com.philippo.algafood.domain.model.User;
import com.philippo.algafood.domain.service.RegisterRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/responsible")
public class RestaurantUserResponsibleController {

    @Autowired
    private RegisterRestaurantService restaurantService;

    @Autowired
    private UserModelAssembler userModelAssembler;

    @GetMapping
    public List<UserModel> list(@PathVariable Long restaurantId){
        Restaurant restaurant = restaurantService.findOrFail(restaurantId);
        return userModelAssembler.toCollectionModel(restaurant.getUsersResponsible());
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disaffiliate(@PathVariable Long restaurantId, @PathVariable Long userId){
        restaurantService.disaffiliateUserRaesponsible(restaurantId, userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void affiliate(@PathVariable Long restaurantId, @PathVariable Long userId){
        restaurantService.affiliateUserResponsible(restaurantId, userId);
    }
}
