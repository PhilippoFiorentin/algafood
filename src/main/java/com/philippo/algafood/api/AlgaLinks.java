package com.philippo.algafood.api;

import com.philippo.algafood.api.controller.*;
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

    public Link linkToPaymentMethod(Long paymentMethodId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PaymentMethodController.class)
                .find(paymentMethodId, null)).withRel(rel);
    }

    public Link linkToPaymentMethod(Long paymentMethodId) {
        return linkToPaymentMethod(paymentMethodId, IanaLinkRelations.SELF.value());
    }

    public Link linkToRestaurantPaymentMethods(Long restaurantId, String rel) {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RestaurantPaymentMethodController.class)
                .list(restaurantId)).withRel(rel);
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

    public Link linkToRestaurantUserResponsibles(Long restaurantId, String rel) {
        return WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(RestaurantUserResponsibleController.class).list(restaurantId))
                .withRel(rel);
    }

    public Link linkToRestaurantUserResponsibles(Long restaurantId) {
        return linkToRestaurantUserResponsibles(restaurantId, IanaLinkRelations.SELF.value());
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
