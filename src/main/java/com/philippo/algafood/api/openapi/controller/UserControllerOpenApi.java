package com.philippo.algafood.api.openapi.controller;

import com.philippo.algafood.api.exceptionhandler.Problem;
import com.philippo.algafood.api.model.UserModel;
import com.philippo.algafood.api.model.input.PasswordInput;
import com.philippo.algafood.api.model.input.UserInput;
import com.philippo.algafood.api.model.input.UserWithPasswordInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

@Api(tags = "Users")
public interface UserControllerOpenApi {

    @ApiOperation("List users")
    CollectionModel<UserModel> list();

    @ApiOperation("Find a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "Invalid user ID",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    }),
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    UserModel find(@ApiParam(value = "User ID", example = "1", required = true) Long userId);

    @ApiOperation("Register user")
    UserModel add(@ApiParam(name = "body", value = "Representation of a new user", required = true) UserWithPasswordInput userInput);

    @ApiOperation("Update user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    UserModel update(
            @ApiParam(value = "User ID", example = "1", required = true) Long userId,
            @ApiParam(name = "body", value = "Representation of a user with new data", required = true) UserInput userInput);

    @ApiOperation("Update user password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "User not found",
                    content = { @Content(schema = @Schema(implementation = Problem.class))
                    })
    })
    void changePassword(
                        @ApiParam(value = "User ID", example = "1", required = true) Long userId,
                        @ApiParam(name = "body", value = "Representation of a new password", required = true) PasswordInput passwordInput);
}
