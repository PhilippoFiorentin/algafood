package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.controller.StatisticController.StatisticsModel;
import com.philippo.algafood.domain.filter.DailySaleFilter;
import com.philippo.algafood.domain.model.dto.DailySale;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Statistics")
public interface StatisticsControllerOpenApi {

    @ApiOperation(value = "Statistics", hidden = true)
    StatisticsModel statistics();

    @ApiOperation("View daily sales statistics")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "restaurantId", value = "Restaurant ID", example = "1", dataType = "int"),
            @ApiImplicitParam(name = "dateCreationStart", value = "Initial date/time of order creation",
                    example = "2024-12-01T00:00:00Z", dataType = "date-time"),
            @ApiImplicitParam(name = "dateCreationEnd", value = "Final date/time of order creation",
                    example = "2024-12-02T23:59:59Z", dataType = "date-time")
    })
    List<DailySale> checkDailySales(DailySaleFilter filter,
                                    @ApiParam(value = "Time offset to be considered in the query in relation to UTC",
                                            defaultValue = "+00:00") String timeOffset);

    ResponseEntity<byte[]> checkDailySalesPdf(DailySaleFilter filter, String timeOffset);
}
