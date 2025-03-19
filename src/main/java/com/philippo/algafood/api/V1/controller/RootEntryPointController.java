package com.philippo.algafood.api.V1.controller;

import com.philippo.algafood.api.V1.AlgaLinks;
import com.philippo.algafood.core.security.AlgaSecurity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    @GetMapping
    @Operation(hidden = true)
    public RootEntryPointModel root(){
        var rootEntryPointModel = new RootEntryPointModel();

        if (algaSecurity.canConsultKitchens()) {
            rootEntryPointModel.add(algaLinks.linkToKitchens("kitchens"));
        }
        if (algaSecurity.canSearchOrders()) {
            rootEntryPointModel.add(algaLinks.linkToOrders("orders"));
        }

        if (algaSecurity.canConsultRestaurants()) {
            rootEntryPointModel.add(algaLinks.linkToRestaurants("restaurants"));
        }

        if (algaSecurity.canConsultUsersGroupsPermissions()) {
            rootEntryPointModel.add(algaLinks.linkToGroups("groups"));
            rootEntryPointModel.add(algaLinks.linkToUsers("users"));
            rootEntryPointModel.add(algaLinks.linkToPermissions("permissions"));
        }

        if (algaSecurity.canConsultPaymentMethods()) {
            rootEntryPointModel.add(algaLinks.linkToPaymentMethods("payment methods"));
        }

        if (algaSecurity.canConsultStates()) {
            rootEntryPointModel.add(algaLinks.linkToStates("states"));
        }

        if (algaSecurity.canConsultCities()) {
            rootEntryPointModel.add(algaLinks.linkToCities("cities"));
        }

        if (algaSecurity.canConsultStatistics()) {
            rootEntryPointModel.add(algaLinks.linkToStatistics("statistics"));
        }

        return rootEntryPointModel;
    }

    public static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }
}
