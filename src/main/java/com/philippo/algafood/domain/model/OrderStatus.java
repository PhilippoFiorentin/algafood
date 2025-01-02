package com.philippo.algafood.domain.model;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {

    CREATED("Created"),
    CONFIRMED("Confirmed", CREATED),
    DELIVERED("Delivered", CONFIRMED),
    CANCELLED("Cancelled", CREATED);

    private final String description;
    private final List<OrderStatus> previousStatus;

    OrderStatus(String description, OrderStatus... previousStatus) {
        this.description = description;
        this.previousStatus = Arrays.asList(previousStatus);
    }

    public String getDescription() {
        return this.description;
    }

    public boolean notAbleToChangeTo(OrderStatus newStatus) {
        return !newStatus.previousStatus.contains(this);
    }

    public boolean ableToChangeTo(OrderStatus newStatus) {
        return !notAbleToChangeTo(newStatus);
    }

}
