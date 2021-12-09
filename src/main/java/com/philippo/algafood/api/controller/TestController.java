package com.philippo.algafood.api.controller;

import com.philippo.algafood.domain.model.Kitchen;
import com.philippo.algafood.domain.model.Restaurant;
import com.philippo.algafood.domain.repository.KitchenRepository;
import com.philippo.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.philippo.algafood.domain.infrastructure.repository.spec.RestaurantSpecs.*;

@RestController
@RequestMapping("/test")
public class TestController
{

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/kitchens/by-name")
    public List<Kitchen> kitchensByName(String name){
        return kitchenRepository.findAllByNameContaining(name);
     }

    @GetMapping("/kitchens/unique-by-name")
    public Optional<Kitchen> kitchenByName(String name){
        return kitchenRepository.findByName(name);
    }

    @GetMapping("/restaurants/by-delivery-fee")
    public List<Restaurant> restaurantsByDeliveryFee(BigDecimal initialFee, BigDecimal finalFee){
        return restaurantRepository.findByDeliveryFeeBetween(initialFee, finalFee);
    }

    @GetMapping("/restaurants/by-name")
    public List<Restaurant> restaurantsByDeliveryFee(String name, Long kitchenId){
        return restaurantRepository.searchByName(name, kitchenId);
    }

    @GetMapping("/restaurants/first-by-name")
    public Optional<Restaurant> restaurantFirstByName(String name){
        return restaurantRepository.findFirstRestaurantByNameContaining(name);
    }

    @GetMapping("/restaurants/top-2-by-name")
    public List<Restaurant> restaurantsTop2ByName(String name){
        return restaurantRepository.findTop2RestaurantByNameContaining(name);
    }

    @GetMapping("/kitchen-exists")
    public Boolean kitchenExists(String name){
        return kitchenRepository.existsByName(name);
    }

    @GetMapping("/restaurants/by-name-and-delivery")
    public List<Restaurant> restaurantsByNameDelivery(String name, BigDecimal initialFee, BigDecimal finalFee){
        return restaurantRepository.find(name, initialFee, finalFee);
    }

    @GetMapping("/count-restaurants-kitchen")
    public Integer restaurantsCountByKitchen(Long kitchenId){
        return restaurantRepository.countByKitchenId(kitchenId);
    }

    @GetMapping("/restaurants/free-delivery")
    public List<Restaurant> restaurantsFreeDelivery(String name){
        return restaurantRepository.findAll(freeDelivery().and(similarName(name)));
    }
}
