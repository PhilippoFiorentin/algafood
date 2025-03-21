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

        Object userId = jwt.getClaim("user_id");

        if (userId == null) {
            return null;
        }

        return Long.valueOf(userId.toString());
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

    public boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean canManageOrders(String orderUuid) {
        return hasAuthority("SCOPE_WRITE") && (hasAuthority("MANAGE_ORDERS")
                || manageOrderRestaurant(orderUuid));
    }

    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }

    public boolean hasReadScope(){
        return hasAuthority("SCOPE_READ");
    }

    public boolean hasWriteScope(){
        return hasAuthority("SCOPE_WRITE");
    }

    public boolean canConsultRestaurants(){
        return hasReadScope() && isAuthenticated();
    }

    public boolean canManageRestaurantRegistration(){
        return hasWriteScope() && hasAuthority("EDIT_RESTAURANTS");
    }

    public boolean canManageRestaurantOperation(Long restaurantId){
        return hasWriteScope() && (hasAuthority("EDIT_RESTAURANTS") || manageRestaurant(restaurantId));
    }

    public boolean canConsultUsersGroupsPermissions(){
        return hasReadScope() && hasAuthority("CONSULT_USERS_GROUPS_PERMISSIONS");
    }

    public boolean canEditUsersGroupsPermissions(){
        return hasWriteScope() && hasAuthority("EDIT_USERS_GROUPS_PERMISSIONS");
    }

    public boolean canSearchOrders(Long clientId, Long restaurantId) {
        return hasReadScope() && (
                hasAuthority("CONSULT_ORDERS") ||
                        authenticatedUserEquals(restaurantId) || manageRestaurant(restaurantId));
    }

    public boolean canSearchOrders(){
        return isAuthenticated() && hasReadScope();
    }

    public boolean canConsultPaymentMethods(){
        return isAuthenticated() && hasReadScope();
    }

    public boolean canConsultCities(){
        return isAuthenticated() && hasReadScope();
    }

    public boolean canConsultStates(){
        return isAuthenticated() && hasReadScope();
    }

    public boolean canConsultKitchens(){
        return isAuthenticated() && hasReadScope();
    }

    public boolean canConsultStatistics(){
        return hasReadScope() && hasAuthority("GENERATE_REPORTS");
    }
}
