package com.philippo.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Kitchen {

//	@NotNull(groups = Groups.KitchenId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@NotBlank
	@Column(nullable = false)
	private String name;

//	@OneToMany(mappedBy = "kitchen")
//	private List<Restaurant> restaurants = new ArrayList<>();
}
