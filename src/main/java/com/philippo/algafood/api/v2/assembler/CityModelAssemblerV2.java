package com.philippo.algafood.api.v2.assembler;

import com.philippo.algafood.api.v2.AlgaLinksV2;
import com.philippo.algafood.api.v2.controller.CityControllerV2;
import com.philippo.algafood.api.v2.model.CityModelV2;
import com.philippo.algafood.domain.model.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CityModelAssemblerV2 extends RepresentationModelAssemblerSupport<City, CityModelV2> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinksV2 algaLinks;

    public CityModelAssemblerV2() {
        super(CityControllerV2.class, CityModelV2.class);
    }

    @Override
    public CityModelV2 toModel(City city) {
        CityModelV2 cityModel = createModelWithId(city.getId(), city);
        modelMapper.map(city, cityModel);

        cityModel.add(algaLinks.linkToCities("cities"));

        return cityModel;
    }

    @Override
    public CollectionModel<CityModelV2> toCollectionModel(Iterable<? extends City> entities) {
        return super.toCollectionModel(entities)
                .add(algaLinks.linkToCities());
    }
}
