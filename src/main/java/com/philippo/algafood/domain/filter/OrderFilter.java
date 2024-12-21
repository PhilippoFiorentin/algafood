package com.philippo.algafood.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderFilter {

    @ApiModelProperty(example = "1", value = "Client id for search filter")
    private Long clientId;

    @ApiModelProperty(example = "1", value = "Restaurant id for search filter")
    private Long restaurantId;

    @ApiModelProperty(example = "2024-12-10T00:00:00Z", value = "Initial creation date/time for search filter")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dateCreationStart;

    @ApiModelProperty(example = "2024-12-20T00:00:00Z", value = "Final creation date/time for search filter")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dateCreationEnd;
}
