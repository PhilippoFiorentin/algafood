package com.philippo.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class OrderItem {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
    private Integer quantity;

//    @Column(nullable = false)
    private BigDecimal unitaryPrice;

//    @Column(nullable = false)
    private BigDecimal totalPrice;

//    @Column(nullable = false)
    private String observation;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private RestaurantOrder restaurantOrder;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;

    public void calculateTotalValue() {
        BigDecimal unitaryPrice = this.getUnitaryPrice();
        Integer quantity = this.getQuantity();

        if (unitaryPrice == null)
            unitaryPrice = BigDecimal.ZERO;

        if(quantity == null)
            quantity = 0;

        this.setTotalPrice(unitaryPrice.multiply(new BigDecimal(quantity)));
    }
}
