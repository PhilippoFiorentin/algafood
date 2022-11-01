package com.philippo.algafood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.philippo.algafood.domain.model.Restaurant;

import java.util.List;

public abstract class KitchenMixin {

    @JsonIgnore
    private List<Restaurant> restaurants;
}
