package com.philippo.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class RestaurantOrder {

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
    private OffsetDateTime creationDate;

    private OffsetDateTime confirmationDate;

    private OffsetDateTime cancellationDate;

    private OffsetDateTime deliveryDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_client_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @Embedded
    private Address deliveryAddress;

    @OneToMany(mappedBy = "restaurantOrder")
    private List<OrderItem> items = new ArrayList<>();

    public void calculateTotalValue() {
        this.total = getItems()
                .stream()
                .map(item -> item.getTotalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setDeliveryFee(){
        setDeliveryFee(getRestaurant().getDeliveryFee());
    }

    public void assignOrderToItems(){
        getItems().forEach(item -> item.setRestaurantOrder(this));
    }
}
