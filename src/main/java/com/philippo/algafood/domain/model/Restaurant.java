package com.philippo.algafood.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant
{
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(name = "delivery_fee", nullable = false)
	private BigDecimal deliveryFee;
	
	@ManyToOne
	@JoinColumn(name ="kitchen_id", nullable = false)
	private Kitchen kitchen;

	@Embedded
	private Address address;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurant_payment_method",
				joinColumns = @JoinColumn(name = "restaurant_id"),
				inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
	private List<PaymentMethod> paymentMethods = new ArrayList<>();
}
