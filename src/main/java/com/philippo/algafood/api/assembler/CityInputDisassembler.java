package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.model.input.CityInput;
import com.philippo.algafood.domain.model.City;
import com.philippo.algafood.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public City toDomainObject(CityInput cityInput){
        return modelMapper.map(cityInput, City.class);
    }

    public void copyToDomainObject(CityInput cityInput, City city){
        city.setState(new State());

        modelMapper.map(cityInput, city);
    }
}
