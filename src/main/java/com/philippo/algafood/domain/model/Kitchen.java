package com.philippo.algafood.domain.model;

import javax.persistence.*;

import com.philippo.algafood.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

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
