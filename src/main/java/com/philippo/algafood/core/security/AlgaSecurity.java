package com.philippo.algafood.core.security;

import com.philippo.algafood.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AlgaSecurity {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId(){
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        return jwt.getClaim("user_id");
    }

    public boolean manageRestaurant(Long restaurantId) {
        return restaurantRepository.existsResponsible(restaurantId, getUserId());
    }
}
