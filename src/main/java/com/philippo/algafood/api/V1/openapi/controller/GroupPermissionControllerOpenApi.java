package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.model.PermissionModel;
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
@Tag(name = "Groups")
public interface GroupPermissionControllerOpenApi {

    @Operation(summary = "List all permissions associated to a group", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "Invalid group ID", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
            @ApiResponse(responseCode = "404", description = "Group not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    CollectionModel<PermissionModel> list(@Parameter(description = "Group ID", example = "1", required = true) Long groupId);

    @Operation(summary = "Permission disassociation with group", responses = {
            @ApiResponse(responseCode = "204", description = "Disassociation successfully completed"),
            @ApiResponse(responseCode = "404", description = "Group or permission not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> disaffiliate(@Parameter(description = "Group ID", example = "1", required = true) Long groupId,
                                      @Parameter(description = "Permission ID", example = "1", required = true) Long permissionId);

    @Operation(summary = "Permission association with group", responses = {
            @ApiResponse(responseCode = "204", description = "Association successfully completed"),
            @ApiResponse(responseCode = "404", description = "Group or permission not found", content = {
                    @Content(schema = @Schema(ref = "Problem")) }),
    })
    ResponseEntity<Void> affiliate(@Parameter(description = "Group ID", example = "1", required = true) Long groupId,
                                   @Parameter(description = "Permission ID", example = "1", required = true) Long permissionId);
}
