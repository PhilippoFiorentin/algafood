package com.philippo.algafood.api.controller.openapi;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.model.CityModel;
import com.philippo.algafood.api.model.input.CityInput;
import com.philippo.algafood.domain.exception.BusinessException;
import com.philippo.algafood.domain.exception.StateNotFoundException;
import com.philippo.algafood.domain.model.City;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cities")
public interface CityControllerOpenApi {

    @ApiOperation("List cities")
    public List<CityModel> list();

    @ApiOperation("Search a city by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Invalid city ID",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "City not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    public CityModel find(@ApiParam(value = "ID of a city", example = "1") Long cityId);

    @ApiOperation("Register a city")
    public CityModel add(@ApiParam(name = "body", value = "Representation of a new city") CityInput cityInput);

    @ApiOperation("Update a city by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "City not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    public CityModel update(@ApiParam(value = "ID of a city", example = "1") Long cityId,
                            @ApiParam(name = "body", value = "representation of a city with the new data") CityInput cityInput);

    @ApiOperation("Delete a city")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "City not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    public void delete(@ApiParam(value = "ID of a city", example = "1") Long cityId);

}
