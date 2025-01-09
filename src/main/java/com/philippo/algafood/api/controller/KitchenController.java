package com.philippo.algafood.api.controller;

import com.philippo.algafood.api.assembler.KitchenInputDisassembler;
import com.philippo.algafood.api.assembler.KitchenModelAssembler;
import com.philippo.algafood.api.model.KitchenModel;
import com.philippo.algafood.api.model.input.KitchenInput;
import com.philippo.algafood.api.openapi.controller.KitchenControllerOpenApi;
import com.philippo.algafood.domain.model.Kitchen;
import com.philippo.algafood.domain.repository.KitchenRepository;
import com.philippo.algafood.domain.service.RegisterKitchenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/v1/kitchens", produces = MediaType.APPLICATION_JSON_VALUE)
public class KitchenController implements KitchenControllerOpenApi {

	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private RegisterKitchenService registerKitchen;

	@Autowired
	private KitchenModelAssembler kitchenModelAssembler;

	@Autowired
	private KitchenInputDisassembler kitchenInputDisassembler;

	@Autowired
	private PagedResourcesAssembler<Kitchen> pagedResourcesAssembler;

	@GetMapping
	public PagedModel<KitchenModel> list(@PageableDefault(size  = 10) Pageable pageable){
		log.info("Listing all kitchens with pages of {} registers", pageable.getPageSize());

		if (true)
			throw new RuntimeException("Exception test");

		Page<Kitchen> kitchenPages = kitchenRepository.findAll(pageable);

		PagedModel<KitchenModel> kitchensPagedModel = pagedResourcesAssembler.toModel(kitchenPages, kitchenModelAssembler);

		return kitchensPagedModel;
	}
	
	@GetMapping("/{kitchenId}")
	public KitchenModel find(@PathVariable Long kitchenId) {
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
