package com.philippo.algafood.core.security;

import com.philippo.algafood.domain.repository.RestaurantOrderRepository;
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

    @Autowired
    private RestaurantOrderRepository restaurantOrderRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Long getUserId(){
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        return jwt.getClaim("user_id");
    }

    public boolean manageRestaurant(Long restaurantId) {

        if (restaurantId == null) {
            return false;
        }

        return restaurantRepository.existsResponsible(restaurantId, getUserId());
    }

    public boolean manageOrderRestaurant(String orderUuid) {
        return restaurantOrderRepository.isOrderManagedBy(orderUuid, getUserId());
    }

    public boolean authenticatedUserEquals(Long userId) {
        return getUserId() != null && userId != null && getUserId().equals(userId);
    }
}
