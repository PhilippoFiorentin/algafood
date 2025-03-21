package com.philippo.algafood.domain.model;

import com.philippo.algafood.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class State {

	@NotNull(groups = Groups.StateId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@NotBlank
	@Column(nullable = false)
	private String name;
}
