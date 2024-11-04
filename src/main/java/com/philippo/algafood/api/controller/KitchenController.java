package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.KitchenInputDisassembler;
import com.philippo.algafood.api.assembler.KitchenModelAssembler;
import com.philippo.algafood.api.model.KitchenModel;
import com.philippo.algafood.api.model.input.KitchenInput;
import com.philippo.algafood.domain.model.Kitchen;
import com.philippo.algafood.domain.repository.KitchenRepository;
import com.philippo.algafood.domain.service.RegisterKitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private RegisterKitchenService registerKitchen;

	@Autowired
	private KitchenModelAssembler kitchenModelAssembler;

	@Autowired
	private KitchenInputDisassembler kitchenInputDisassembler;
	
	@GetMapping
	public List<KitchenModel> listAllKitchens(){
		return kitchenModelAssembler.toCollectionModel(kitchenRepository.findAll());
	}
	
	@GetMapping("/{kitchenId}")
	public KitchenModel findKitchen(@PathVariable Long kitchenId) {
		Kitchen kitchen = registerKitchen.findOrFail(kitchenId);

		return kitchenModelAssembler.toModel(kitchen);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public KitchenModel add(@RequestBody @Valid KitchenInput kitchenInput) {
		Kitchen kitchen = kitchenInputDisassembler.toDomainObject(kitchenInput);
		return kitchenModelAssembler.toModel(registerKitchen.save(kitchen));
	}
	
	@PutMapping("/{kitchenId}")
	public KitchenModel update(@PathVariable Long kitchenId, @RequestBody @Valid KitchenInput kitchenInput){
		Kitchen currentKitchen = registerKitchen.findOrFail(kitchenId);

		kitchenInputDisassembler.copyToDomainObject(kitchenInput, currentKitchen);
		
		currentKitchen = registerKitchen.save(currentKitchen);

		return kitchenModelAssembler.toModel(currentKitchen);
	}

	@DeleteMapping("/{kitchenId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long kitchenId){
		registerKitchen.delete(kitchenId);
	}
}
