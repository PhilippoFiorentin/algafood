package com.philippo.algafood.api.V1.controller;

import com.philippo.algafood.api.V1.AlgaLinks;
import com.philippo.algafood.api.V1.openapi.controller.StatisticsControllerOpenApi;
import com.philippo.algafood.core.security.CheckSecurity;
import com.philippo.algafood.domain.filter.DailySaleFilter;
import com.philippo.algafood.domain.model.dto.DailySale;
import com.philippo.algafood.domain.service.SaleQueryService;
import com.philippo.algafood.domain.service.SaleReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics")
public class StatisticController implements StatisticsControllerOpenApi {

    @Autowired
    private SaleReportService saleReportService;

    @Autowired
    private SaleQueryService saleQueryService;

    @Autowired
    private AlgaLinks algaLinks;

    @CheckSecurity.Statistics.CanConsult
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public StatisticsModel statistics() {
        var statisticsModel = new StatisticsModel();

        statisticsModel.add(algaLinks.linkToDailySalesOrders("daily-sales"));

        return statisticsModel;
    }

    @CheckSecurity.Statistics.CanConsult
    @GetMapping(path = "/v1/daily-sales", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailySale> checkDailySales(DailySaleFilter filter,
                                           @RequestParam(required=false, defaultValue = "+00:00") String timeOffset) {
        return saleQueryService.checkDailySales(filter, timeOffset);
    }

    @CheckSecurity.Statistics.CanConsult
    @GetMapping(path = "/daily-sales", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> checkDailySalesPdf(DailySaleFilter filter,
                                             @RequestParam(required=false, defaultValue = "+00:00") String timeOffset) {

        byte[] pdfBytes = saleReportService.issueDailySales(filter, timeOffset);

        var headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=daily-sales.pdf");

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).headers(headers).body(pdfBytes);
    }

    public static class StatisticsModel extends RepresentationModel<StatisticsModel> {

    }
}
