package com.philippo.algafood.domain.service;

import com.philippo.algafood.domain.filter.DailySaleFilter;
import net.sf.jasperreports.engine.JRException;

public interface SaleReportService {

    byte[] issueDailySales(DailySaleFilter filter, String timeOffset);
}
