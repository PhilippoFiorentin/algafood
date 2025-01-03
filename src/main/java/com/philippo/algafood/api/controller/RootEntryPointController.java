package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.AlgaLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public RootEntryPointModel root(){
        var rootEntryPointModel = new RootEntryPointModel();

        rootEntryPointModel.add(algaLinks.linkToKitchens("kitchens"));
        rootEntryPointModel.add(algaLinks.linkToOrders("orders"));
        rootEntryPointModel.add(algaLinks.linkToRestaurants("restaurants"));
        rootEntryPointModel.add(algaLinks.linkToGroups("groups"));
        rootEntryPointModel.add(algaLinks.linkToUsers("users"));
        rootEntryPointModel.add(algaLinks.linkToPermissions("permissions"));
        rootEntryPointModel.add(algaLinks.linkToPaymentMethods("payment methods"));
        rootEntryPointModel.add(algaLinks.linkToStates("states"));
        rootEntryPointModel.add(algaLinks.linkToCities("cities"));
        rootEntryPointModel.add(algaLinks.linkToStatistics("statistics"));

        return rootEntryPointModel;
    }

    public static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }
}
