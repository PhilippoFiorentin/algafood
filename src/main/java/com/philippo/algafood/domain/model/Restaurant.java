package com.philippo.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant
{
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(nullable = false)
	private String name;
	
	@Column(name = "delivery_fee", nullable = false)
	private BigDecimal deliveryFee;

//	@JsonIgnore
	@ManyToOne /*(fetch = FetchType.LAZY)*/
//	@JsonIgnoreProperties("hibernateLazyInitializer")
	@JoinColumn(name ="kitchen_id", nullable = false)
	private Kitchen kitchen;

	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition="datetime")
	private LocalDateTime registerDate;

	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition="datetime")
	private LocalDateTime updateDate;

	@JsonIgnore
	@Embedded
	private Address address;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurant_payment_method",
				joinColumns = @JoinColumn(name = "restaurant_id"),
				inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
	private List<PaymentMethod> paymentMethods = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private List<Product> products = new ArrayList<>();
}
