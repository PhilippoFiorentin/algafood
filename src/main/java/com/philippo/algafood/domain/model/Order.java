package com.philippo.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Order {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(nullable = false)
    private BigDecimal deliveryFee;

    @Column(nullable = false)
    private BigDecimal total;

    @CreationTimestamp
    private LocalDateTime creationDate;

    private LocalDateTime confirmationDate;

    private LocalDateTime cancellationDate;

    private LocalDateTime deliveryDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_client_id", nullable = false)
    private User user;

    private OrderStatus status;

    @Embedded
    private Address deliveryAddress;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();
}
