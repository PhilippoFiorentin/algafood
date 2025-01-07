package com.philippo.algafood.api.v2.assembler;

import com.philippo.algafood.api.v2.model.input.CityInputV2;
import com.philippo.algafood.domain.model.City;
import com.philippo.algafood.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityInputDisassemblerV2 {

    @Autowired
    private ModelMapper modelMapper;

    public City toDomainObject(CityInputV2 cityInput){
        return modelMapper.map(cityInput, City.class);
    }

    public void copyToDomainObject(CityInputV2 cityInput, City city){
        city.setState(new State());

        modelMapper.map(cityInput, city);
    }
}
