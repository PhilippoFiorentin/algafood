package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.controller.StatisticController.StatisticsModel;
import com.philippo.algafood.domain.filter.DailySaleFilter;
import com.philippo.algafood.domain.model.dto.DailySale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Statistics")
public interface StatisticsControllerOpenApi {

    @Operation(hidden = true)
    StatisticsModel statistics();

    @Operation(
            summary = "View daily sales statistics",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "restaurantId", description = "Restaurant ID", example = "1", schema = @Schema(type = "integer")),
                    @Parameter(in = ParameterIn.QUERY, name = "startCreationDate", description = "Initial date/time of order creation", example = "2025-03-19T00:00:00Z", schema = @Schema(type = "string", format = "date-time")),
                    @Parameter(in = ParameterIn.QUERY, name = "endCreationDate", description = "Final date/time of order creation", example = "2025-03-20T23:59:59Z", schema = @Schema(type = "string", format = "date-time"))
            },
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DailySale.class))),
                            @Content(mediaType = "application/pdf", schema = @Schema(type = "string", format = "binary")),
                    }),
            }
    )
    List<DailySale> checkDailySales(@Parameter(hidden = true) DailySaleFilter filter,
                                    @Parameter(description = "Time offset to be considered in the query in relation to UTC", schema = @Schema(type = "string", defaultValue = "+00:00")) String timeOffset);

    @Operation(hidden = true)
    ResponseEntity<byte[]> checkDailySalesPdf(DailySaleFilter filter, String timeOffset);
}
