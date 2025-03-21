package com.philippo.algafood.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DailySale {

    private Date date;
    private Long totalSales;
    private BigDecimal totalBilled;

    public DailySale(java.sql.Date date, Long totalSales,  BigDecimal totalBilled) {
        this.date = new Date(date.getTime());
        this.totalSales = totalSales;
        this.totalBilled = totalBilled;
    }
}
