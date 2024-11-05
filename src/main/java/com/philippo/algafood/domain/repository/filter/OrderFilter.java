package com.philippo.algafood.domain.repository.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OrderFilter {

    private Long clientId;
    private Long restaurantId;
    private OffsetDateTime dateCreationStart;
    private OffsetDateTime dateCreationEnd;
}
