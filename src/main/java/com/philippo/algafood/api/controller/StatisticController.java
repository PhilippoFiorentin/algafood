package com.philippo.algafood.api.controller;

import com.philippo.algafood.domain.filter.DailySaleFilter;
import com.philippo.algafood.domain.model.dto.DailySale;
import com.philippo.algafood.domain.service.SaleQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    private SaleQueryService saleQueryService;

    @GetMapping("/daily-sales")
    public List<DailySale> checkDailySales(DailySaleFilter filter) {
        return saleQueryService.checkDailySales(filter);
    }
}
