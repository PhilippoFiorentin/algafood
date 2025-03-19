package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.UserModel;
import com.philippo.algafood.api.V1.model.input.PasswordInput;
import com.philippo.algafood.api.V1.model.input.UserInput;
import com.philippo.algafood.api.V1.model.input.UserWithPasswordInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Users")
public interface UserControllerOpenApi {

    @Operation(summary = "List all users")
    CollectionModel<UserModel> list();

    @Operation(summary = "Find a user by ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid user ID", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
            @ApiResponse(responseCode = "404", description = "User not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    UserModel find(@Parameter(description = "User ID", example = "1", required = true) Long userId);

    @Operation(summary = "Register a user", responses = {
            @ApiResponse(responseCode = "201", description = "Registered User"),
    })
    UserModel add(@RequestBody(description = "Representation of a new user", required = true) UserWithPasswordInput userInput);

    @Operation(summary = "Update a user by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Updated user"),
            @ApiResponse(responseCode = "404", description = "User not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) })
    })
    UserModel update(@Parameter(description = "User ID", example = "1", required = true) Long userId,
                     @RequestBody(description = "Representation of a user with new data", required = true) UserInput userInput);

    @Operation(summary = "Update a user password", responses = {
            @ApiResponse(responseCode = "200", description = "Password changed successfully"),
            @ApiResponse(responseCode = "404", description = "User not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) })
    })
    void changePassword(@Parameter(description = "User ID", example = "1", required = true) Long userId,
                        @RequestBody(description = "Representation of a new password", required = true) PasswordInput passwordInput);
}
