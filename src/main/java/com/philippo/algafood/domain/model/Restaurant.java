package com.philippo.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.philippo.algafood.core.validation.Groups;
import com.philippo.algafood.core.validation.Multiple;
import com.philippo.algafood.core.validation.ValueZeroIncludesDescription;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@ValueZeroIncludesDescription(valueField="deliveryFee", descriptionField="name", mandatoryDescription="Delivery free")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@NotNull
	@PositiveOrZero
	@Multiple(number = 5)
	@Column(name = "delivery_fee", nullable = false)
	private BigDecimal deliveryFee;

	@Valid
	@ConvertGroup(from = Default.class, to = Groups.StateId.class)
	@NotNull
	@ManyToOne
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
