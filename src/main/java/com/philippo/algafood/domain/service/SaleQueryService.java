package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.filter.DailySaleFilter;
import com.philippo.algafood.domain.model.dto.DailySale;

import java.util.List;

public interface SaleQueryService {

    List<DailySale> checkDailySales(DailySaleFilter filter);
}
