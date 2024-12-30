package com.philippo.algafood.api.assembler;

import com.philippo.algafood.api.controller.CityController;
import com.philippo.algafood.api.controller.StateController;
import com.philippo.algafood.api.model.CityModel;
import com.philippo.algafood.domain.model.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CityModelAssembler extends RepresentationModelAssemblerSupport<City, CityModel> {

    @Autowired
    private ModelMapper modelMapper;

    public CityModelAssembler() {
        super(CityController.class, CityModel.class);
    }

    @Override
    public CityModel toModel(City city) {
        CityModel cityModel = createModelWithId(city.getId(), city);

        modelMapper.map(city, cityModel);

        cityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CityController.class).list()).withRel("cities"));

        cityModel.getState().add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(StateController.class).find(cityModel.getState().getId())).withSelfRel());

        return cityModel;
    }

    @Override
    public CollectionModel<CityModel> toCollectionModel(Iterable<? extends City> entities) {
        return super.toCollectionModel(entities)
                .add(WebMvcLinkBuilder.linkTo(CityController.class).withSelfRel());
    }
}
