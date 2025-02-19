package com.philippo.algafood.api.V1.assembler;

import com.philippo.algafood.api.V1.AlgaLinks;
import com.philippo.algafood.api.V1.controller.CityController;
import com.philippo.algafood.api.V1.model.CityModel;
import com.philippo.algafood.core.security.AlgaSecurity;
import com.philippo.algafood.domain.model.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CityModelAssembler extends RepresentationModelAssemblerSupport<City, CityModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public CityModelAssembler() {
        super(CityController.class, CityModel.class);
    }

    @Override
    public CityModel toModel(City city) {
        CityModel cityModel = createModelWithId(city.getId(), city);
        modelMapper.map(city, cityModel);

        if (algaSecurity.canConsultCities()) {
            cityModel.add(algaLinks.linkToCities("cities"));
        }

        if (algaSecurity.canConsultStates()) {
            cityModel.getState().add(algaLinks.linkToState(cityModel.getState().getId()));
        }

        return cityModel;
    }

    @Override
    public CollectionModel<CityModel> toCollectionModel(Iterable<? extends City> entities) {
        CollectionModel<CityModel> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.canConsultCities()) {
            collectionModel.add(algaLinks.linkToCities());
        }

        return collectionModel;
    }
}
