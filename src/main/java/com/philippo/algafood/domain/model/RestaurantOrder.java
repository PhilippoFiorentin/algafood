package com.philippo.algafood.domain.model;

import com.philippo.algafood.domain.event.OrderCancelledEvent;
import com.philippo.algafood.domain.event.OrderConfirmedEvent;
import com.philippo.algafood.domain.exception.BusinessException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Entity
public class RestaurantOrder extends AbstractAggregateRoot<RestaurantOrder> {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_client_id", nullable = false)
    private User client;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @Embedded
    private Address deliveryAddress;

    @OneToMany(mappedBy = "restaurantOrder", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    public void calculateTotalValue() {
        getItems().forEach(OrderItem::calculateTotalPrice);

        this.subtotal = getItems()
                .stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.total = this.subtotal.add(this.deliveryFee);
    }

    public void confirm(){
        setStatus(OrderStatus.CONFIRMED);
        setConfirmationDate(OffsetDateTime.now());

        registerEvent(new OrderConfirmedEvent(this));
    }

    public void deliver(){
        setStatus(OrderStatus.DELIVERED);
        setDeliveryDate(OffsetDateTime.now());
    }

    public void cancel(){
        setStatus(OrderStatus.CANCELLED);
        setCancellationDate(OffsetDateTime.now());

        registerEvent(new OrderCancelledEvent(this));
    }

    public boolean canBeConfirmed(){
        return getStatus().ableToChangeTo(OrderStatus.CONFIRMED);
    }

    public boolean canBeDelivered(){
        return getStatus().ableToChangeTo(OrderStatus.DELIVERED);
    }

    public boolean canBeCancelled(){
        return getStatus().ableToChangeTo(OrderStatus.CANCELLED);
    }

    private void setStatus(OrderStatus newStatus) {
        if (getStatus().notAbleToChangeTo(newStatus))
            throw new BusinessException(String.format(
                    "The order status %s cannot be changed from %s to %s",
                    getUuid(), getStatus().getDescription(), newStatus.getDescription()));

        this.status = newStatus;
    }

    @PrePersist
    private void generateUuid(){
        setUuid(UUID.randomUUID().toString());
    }
}
