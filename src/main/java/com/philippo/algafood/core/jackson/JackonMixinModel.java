package com.philippo.algafood.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.philippo.algafood.domain.model.*;
import com.philippo.algafood.api.model.mixin.CityMixin;
import org.springframework.stereotype.Component;

@Component
public class JackonMixinModel extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public JackonMixinModel(){
        setMixInAnnotation(City.class, CityMixin.class);
    }

}
