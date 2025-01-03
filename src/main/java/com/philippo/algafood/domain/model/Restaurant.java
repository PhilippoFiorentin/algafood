package com.philippo.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Column(name = "delivery_fee", nullable = false)
	private BigDecimal deliveryFee;

	@ManyToOne
	@JoinColumn(name ="kitchen_id", nullable = false)
	private Kitchen kitchen;

	@Embedded
	private Address address;

	private Boolean active = Boolean.TRUE;

	private Boolean open = Boolean.FALSE;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition="datetime")
	private OffsetDateTime registerDate;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition="datetime")
	private OffsetDateTime updateDate;

	@ManyToMany
	@JoinTable(name = "restaurant_payment_method",
				joinColumns = @JoinColumn(name = "restaurant_id"),
				inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
	private Set<PaymentMethod> paymentMethods = new HashSet<>() {
	};

	@OneToMany(mappedBy = "restaurant")
	private List<Product> products = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "restaurant_user_responsible",
			joinColumns = @JoinColumn(name = "restaurant_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> responsibleUsers = new HashSet<>() {
	};

	public void activate(){
		setActive(true);
	}

	public boolean isActive(){
		return this.active;
	}

	public void deactivate(){
		setActive(false);
	}

	public boolean isInactive(){
		return !isActive();
	}

	public void open(){
		setOpen(true);
	}

	public boolean isOpen(){
		return this.open;
	}

	public void close(){
		setOpen(false);
	}

	public boolean isClosed(){
		return !isOpen();
	}

	public boolean openingAllowed(){
		return isActive() && isClosed();
	}

	public boolean closingAllowed(){
		return isOpen();
	}

	public boolean activationAllowed(){
		return isInactive();
	}

	public boolean removePaymentMethod(PaymentMethod paymentMethod){
		return getPaymentMethods().remove(paymentMethod);
	}

	public boolean addPaymentMethod(PaymentMethod paymentMethod){
		return getPaymentMethods().add(paymentMethod);
	}

	public boolean removeUserResponsible(User user){
		return getResponsibleUsers().remove(user);
	}

	public boolean addUserResponsible(User user){
		return getResponsibleUsers().add(user);
	}

	public boolean acceptPaymentMethod(PaymentMethod paymentMethod){
		return getPaymentMethods().contains(paymentMethod);
	}

	public boolean dontAcceptPaymentMethod(PaymentMethod paymentMethod){
		return !acceptPaymentMethod(paymentMethod);
	}
}
