package com.philippo.algafood.api.v1;

import com.philippo.algafood.api.v1.controller.*;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class AlgaLinks {

    public static final TemplateVariables PAGINATION_VARIABLES = new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
    );

    public static final TemplateVariables PROJECTION_VARIABLES = new TemplateVariables(
        new TemplateVariable("projection", TemplateVariable.VariableType.REQUEST_PARAM)
    );

    public Link linkToOrders(String rel) {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("clientId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dateCreationStart", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dateCreationEnd", TemplateVariable.VariableType.REQUEST_PARAM)
        );

        String ordersUrl = WebMvcLinkBuilder.linkTo(RestaurantOrderController.class).toUri().toString();

        return new Link(UriTemplate.of(ordersUrl, PAGINATION_VARIABLES.concat(filterVariables)), rel);
    }

    public Link linkToDailySalesOrders(String rel) {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dateCreationStart", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("dateCreationEnd", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("timeOffset", TemplateVariable.VariableType.REQUEST_PARAM)
        );

        String statisticsUrl = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                        .methodOn(StatisticController.class)
                        .checkDailySales(null, null)).toUri().toString();

        return new Link(UriTemplate.of(statisticsUrl, filterVariables), rel);

    }

    public Link linkToStatistics(String rel) {
        return WebMvcLinkBuilder.linkTo(StatisticController.class).withRel(rel);
    }

    public Link linkToOrderConfirmation(String orderCode, String rel){
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderFlowController.class).confirm(orderCode)).withRel(rel);
    }

    public Link linkToOrderDelivery(String orderCode, String rel){
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderFlowController.class).deliver(orderCode)).withRel(rel);
    }

    public Link linkToOrderCancellation(String orderCode, String rel){
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderFlowController.class).cancel(orderCode)).withRel(rel);
    }

    public Link linkToRestaurant(Long restaurantId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestaurantController.class)
                .find(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurants(String rel) {
        String restaurantUrl = WebMvcLinkBuilder.linkTo(RestaurantController.class).toUri().toString();

        return new Link(UriTemplate.of(restaurantUrl, PROJECTION_VARIABLES), rel);
    }

    public Link linkToRestaurants() {
        return linkToRestaurants(IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurant(Long restaurantId) {
        return linkToRestaurant(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToUser(Long userId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).find(userId)).withRel(rel);
    }

    public Link linkToUser(Long userId) {
        return linkToUser(userId, IanaLinkRelations.SELF.value());
    }

    public Link linkToUsers(String rel) {
        return WebMvcLinkBuilder.linkTo(UserController.class).withRel(rel);
    }

    public Link linkToUsers() {
        return linkToUsers(IanaLinkRelations.SELF.value());
    }

    public Link linkToUserGroups(Long userId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserGroupController.class).list(userId)).withRel(rel);
    }

    public Link linkToUserGroups(Long userId) {
        return linkToUserGroups(userId, IanaLinkRelations.SELF.value());
    }

    public Link linkToAffiliateUserGroup(Long userId, String rel) {
        return WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserGroupController.class).affiliate(userId, null)).withRel(rel);
    }

    public Link linkToDisaffiliateUserGroup(Long userId, Long groupId, String rel) {
        return WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UserGroupController.class).disaffiliate(userId, groupId)).withRel(rel);
    }

    public Link linkToGroupPermissions(Long groupId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GroupPermissionController.class).list(groupId)).withRel(rel);
    }

    public Link linkToGroupPermissions(Long groupId) {
        return linkToGroupPermissions(groupId, IanaLinkRelations.SELF.value());
    }

    public Link linkToGroups(String rel) {
        return WebMvcLinkBuilder.linkTo(GroupController.class).withRel(rel);
    }

    public Link linkToGroups() {
        return linkToGroups(IanaLinkRelations.SELF.value());
    }

    public Link linkToPermissions(String rel) {
        return WebMvcLinkBuilder.linkTo(PermissionController.class).withRel(rel);
    }

    public Link linkToPermissions() {
        return linkToPermissions(IanaLinkRelations.SELF.value());
    }

    public Link linkToDisaffiliateGroupPermission(Long groupId, Long permissionId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GroupPermissionController.class)
                .disaffiliate(groupId, permissionId)).withRel(rel);
    }

    public Link linkToAffiliateGroupPermission(Long groupId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(GroupPermissionController.class)
                .affiliate(groupId, null)).withRel(rel);
    }

    public Link linkToPaymentMethod(Long paymentMethodId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PaymentMethodController.class)
                .find(paymentMethodId, null)).withRel(rel);
    }

    public Link linkToPaymentMethod(Long paymentMethodId) {
        return linkToPaymentMethod(paymentMethodId, IanaLinkRelations.SELF.value());
    }

    public Link linkToPaymentMethods(String rel) {
        return WebMvcLinkBuilder.linkTo(PaymentMethodController.class).withRel(rel);
    }

    public Link linkToPaymentMethods() {
        return linkToPaymentMethods(IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantPaymentMethods(Long restaurantId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestaurantPaymentMethodController.class)
                .list(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantPaymentMethods(Long restaurantId) {
        return linkToRestaurantPaymentMethods(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToDisaffiliateRestaurantPaymentMethods(Long restaurantId, Long paymentMethodId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestaurantPaymentMethodController.class)
                .disaffiliate(restaurantId, paymentMethodId)).withRel(rel);
    }

    public Link linkToAffiliateRestaurantPaymentMethods(Long restaurantId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestaurantPaymentMethodController.class)
                .affiliate(restaurantId, null)).withRel(rel);
    }

    public Link linkToCity(Long cityId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(CityController.class).find(cityId)).withRel(rel);
    }

    public Link linkToCity(Long cityId) {
        return linkToCity(cityId, IanaLinkRelations.SELF.value());
    }

    public Link linkToCities(String rel) {
        return WebMvcLinkBuilder.linkTo(CityController.class).withRel(rel);
    }

    public Link linkToCities() {
        return linkToCities(IanaLinkRelations.SELF.value());
    }

    public Link linkToProduct(Long restaurantId, Long productId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestaurantProductController.class)
                .find(restaurantId, productId)).withRel(rel);
    }

    public Link linkToProduct(Long restaurantId, Long productId) {
        return linkToProduct(restaurantId, productId, IanaLinkRelations.SELF.value());
    }

    public Link linkToProducts(Long restaurantId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestaurantProductController.class)
                .list(restaurantId, null)).withRel(rel);
    }

    public Link linkToProducts(Long restaurantId) {
        return linkToProducts(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToProducts(String rel) {
        return WebMvcLinkBuilder.linkTo(RestaurantProductController.class).withRel(rel);
    }

    public Link linkToProducts() {
        return linkToProducts(IanaLinkRelations.SELF.value());
    }

    public Link linkToProductPhoto(Long restaurantId, Long productId, String rel){
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestaurantProductPhotoController.class)
                .find(restaurantId, productId)).withRel(rel);
    }

    public Link linkToProductPhoto(Long restaurantId, Long productId){
        return linkToProductPhoto(restaurantId, productId, IanaLinkRelations.SELF.value());
    }

    public Link linkToState(Long stateId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StateController.class).find(stateId)).withRel(rel);
    }

    public Link linkToState(Long stateId) {
        return linkToState(stateId, IanaLinkRelations.SELF.value());
    }

    public Link linkToStates(String rel){
        return WebMvcLinkBuilder.linkTo(StateController.class).withRel(rel);
    }

    public Link linkToStates() {
        return linkToStates(IanaLinkRelations.SELF.value());
    }

    public Link linkToKitchen(Long kitchenId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(KitchenController.class).find(kitchenId)).withRel(rel);
    }

    public Link linkToKitchen(Long kitchenId) {
        return linkToKitchen(kitchenId, IanaLinkRelations.SELF.value());
    }

    public Link linkToKitchens(String rel) {
        return WebMvcLinkBuilder.linkTo(KitchenController.class).withRel(rel);
    }

    public Link linkToKitchens() {
        return linkToKitchens(IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantResponsibleUser(Long restaurantId, String rel) {
        return WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(RestaurantResponsibleUserController.class).list(restaurantId))
                .withRel(rel);
    }

    public Link linkToRestaurantResponsibleUser(Long restaurantId) {
        return linkToRestaurantResponsibleUser(restaurantId, IanaLinkRelations.SELF.value());
    }

    public Link linkToDisaffiliateRestaurantResponsibleUser(Long restaurantId, Long userId, String rel) {
        return WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(RestaurantResponsibleUserController.class)
                        .disaffiliate(restaurantId, userId)).withRel(rel);
    }

    public Link linkToAffiliateRestaurantResponsibleUser(Long restaurantId, String rel) {
        return WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(RestaurantResponsibleUserController.class)
                        .affiliate(restaurantId, null)).withRel(rel);
    }

    public Link linkToRestaurantOpening(Long restaurantId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestaurantController.class)
                .open(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantClosing(Long restaurantId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestaurantController.class)
                .close(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantActivation(Long restaurantId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestaurantController.class)
                .activate(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantInactivation(Long restaurantId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestaurantController.class)
                .deactivate(restaurantId)).withRel(rel);
    }
}
