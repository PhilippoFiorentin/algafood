package com.philippo.algafood.api.V1.openapi.controller;

import com.philippo.algafood.api.V1.controller.StatisticController.StatisticsModel;
import com.philippo.algafood.domain.filter.DailySaleFilter;
import com.philippo.algafood.domain.model.dto.DailySale;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StatisticsControllerOpenApi {

    StatisticsModel statistics();

    List<DailySale> checkDailySales(DailySaleFilter filter, String timeOffset);

    ResponseEntity<byte[]> checkDailySalesPdf(DailySaleFilter filter, String timeOffset);
}
