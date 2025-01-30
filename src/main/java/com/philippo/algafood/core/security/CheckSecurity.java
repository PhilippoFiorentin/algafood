package com.philippo.algafood.core.security;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {

    public @interface Kitchens {

        @PreAuthorize("@algaSecurity.canConsultKitchens()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_KITCHENS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }

    }

    public @interface Restaurants {

        @PreAuthorize("@algaSecurity.canConsultRestaurants()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("@algaSecurity.canManageRestaurantRegistration()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanManageRegister { }

        @PreAuthorize("@algaSecurity.canManageRestaurantOperation(#restaurantId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanManageOperation { }

    }

    public @interface RestaurantOrders {

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @PostAuthorize("hasAuthority('CONSULT_ORDERS') or " +
                "@algaSecurity.authenticatedUserEquals(returnObject.client.id) or " +
                "@algaSecurity.manageRestaurant(returnObject.restaurant.id)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanFind { }

        @PreAuthorize("@algaSecurity.canSearchOrders(#filter.clientId, #filter.restaurantId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanSearch { }

        @PreAuthorize("@algaSecurity.canManageOrders(#orderUuid)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanManageOrders { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanCreate { }

    }

    public @interface PaymentMethods {

        @PreAuthorize("@algaSecurity.canConsultPaymentMethods()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_PAYMENT_METHODS')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }

    }

    public @interface Cities {

        @PreAuthorize("@algaSecurity.canConsultCities()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_CITIES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }

    }

    public @interface States {

        @PreAuthorize("@algaSecurity.canConsultStates()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_STATES')")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }

    }

    public @interface UsersGroupsPermissions {

        @PreAuthorize("@algaSecurity.canConsultUsersGroupsPermissions()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

        @PreAuthorize("@algaSecurity.canEditUsersGroupsPermissions()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanEdit { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and @algaSecurity.authenticatedUserEquals(#userId)")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanChangePassword { }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDIT_USERS_GROUPS_PERMISSIONS') or " +
                "@algaSecurity.authenticatedUserEquals(#userId))")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanChangeUser { }

    }

    public @interface Statistics {

        @PreAuthorize("@algaSecurity.canConsultStatistics()")
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        public @interface CanConsult { }

    }
}
