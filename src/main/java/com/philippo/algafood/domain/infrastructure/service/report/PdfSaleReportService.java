package com.philippo.algafood.domain.infrastructure.service.report;

import com.philippo.algafood.domain.filter.DailySaleFilter;
import com.philippo.algafood.domain.service.SaleQueryService;
import com.philippo.algafood.domain.service.SaleReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;

@Service
public class PdfSaleReportService implements SaleReportService {

    @Autowired
    private SaleQueryService saleQueryService;

    @Override
    public byte[] issueDailySales(DailySaleFilter filter, String timeOffset) {

        try{
            var inputStream = this.getClass().getResourceAsStream("/reports/daily-sales.jasper");

            var params = new HashMap<String, Object>();
            params.put("REPORT_LOCALE", new Locale("en", "UK"));

            var dailySales = saleQueryService.checkDailySales(filter,timeOffset);
            var dataSource = new JRBeanCollectionDataSource(dailySales);

            var jasperPrint = JasperFillManager.fillReport(inputStream, params, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Unable to issue daily sales report", e);
        }
    }
}
