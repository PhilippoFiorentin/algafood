package com.philippo.algafood.domain.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.philippo.algafood.domain.model.State;

public abstract class CityMixin {

    @JsonIgnoreProperties(value = "name", allowGetters = true)
    private State state;
}
