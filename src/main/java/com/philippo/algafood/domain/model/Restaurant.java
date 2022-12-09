package com.philippo.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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

	@Column(nullable = false)
	private String name;

	@Multiple(number = 5)
	@Column(name = "delivery_fee", nullable = false)
	private BigDecimal deliveryFee;

	@ManyToOne
	@JoinColumn(name ="kitchen_id", nullable = false)
	private Kitchen kitchen;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition="datetime")
	private OffsetDateTime registerDate;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition="datetime")
	private OffsetDateTime updateDate;

	@Embedded
	private Address address;

	private Boolean active = Boolean.TRUE;

	@ManyToMany
	@JoinTable(name = "restaurant_payment_method",
				joinColumns = @JoinColumn(name = "restaurant_id"),
				inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
	private List<PaymentMethod> paymentMethods = new ArrayList<>();

	@OneToMany(mappedBy = "restaurant")
	private List<Product> products = new ArrayList<>();

	public void activate(){
		setActive(true);
	}

	public void deactivate(){
		setActive(false);
	}
}
