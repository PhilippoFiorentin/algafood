package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.GroupModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Users")
public interface UserGroupControllerOpenApi {

    @Operation(summary = "List groups associated to a user", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "User not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    CollectionModel<GroupModel> list(@Parameter(description = "User ID", example = "1", required = true) Long userId);

    @Operation(summary = "Disassociating a group with a user", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "User not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> disaffiliate(@Parameter(description = "User ID", example = "1", required = true) Long userId,
                                      @Parameter(description = "Group ID", example = "1", required = true) Long groupId);

    @Operation(summary = "Associating a group with a user", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "User not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> affiliate(@Parameter(description = "User ID", example = "1", required = true) Long userId,
                                   @Parameter(description = "Group ID", example = "1", required = true) Long groupId);
}
