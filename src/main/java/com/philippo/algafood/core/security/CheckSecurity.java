package com.philippo.algafood.core.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {

    @interface Kitchens {

        @PreAuthorize("@algaSecurity.canConsultKitchens()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanConsult { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_KITCHENS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanEdit { }

    }

    @interface Restaurants {

        @PreAuthorize("@algaSecurity.canConsultRestaurants()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanConsult { }

        @PreAuthorize("@algaSecurity.canManageRestaurantRegistration()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanManageRegister { }

        @PreAuthorize("@algaSecurity.canManageRestaurantOperation(#restaurantId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanManageOperation { }

    }

    @interface RestaurantOrders {

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @PostAuthorize("hasAuthority('CONSULT_ORDERS') or " +
                "@algaSecurity.authenticatedUserEquals(returnObject.client.id) or " +
                "@algaSecurity.manageRestaurant(returnObject.restaurant.id)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanFind { }

        @PreAuthorize("@algaSecurity.canSearchOrders(#filter.clientId, #filter.restaurantId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanSearch { }

        @PreAuthorize("@algaSecurity.canManageOrders(#orderUuid)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanManageOrders { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanCreate { }

    }

    @interface PaymentMethods {

        @PreAuthorize("@algaSecurity.canConsultPaymentMethods()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanConsult { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_PAYMENT_METHODS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanEdit { }

    }

    @interface Cities {

        @PreAuthorize("@algaSecurity.canConsultCities()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanConsult { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_CITIES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanEdit { }

    }

    @interface States {

        @PreAuthorize("@algaSecurity.canConsultStates()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanConsult { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_STATES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanEdit { }

    }

    @interface UsersGroupsPermissions {

        @PreAuthorize("@algaSecurity.canConsultUsersGroupsPermissions()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanConsult { }

        @PreAuthorize("@algaSecurity.canEditUsersGroupsPermissions()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanEdit { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and @algaSecurity.authenticatedUserEquals(#userId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanChangePassword { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDIT_USERS_GROUPS_PERMISSIONS') or " +
                "@algaSecurity.authenticatedUserEquals(#userId))")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanChangeUser { }

    }

    @interface Statistics {

        @PreAuthorize("@algaSecurity.canConsultStatistics()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @interface CanConsult { }

    }
}
