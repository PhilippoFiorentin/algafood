package com.philippo.algafood.api.v2.controller;

import com.philippo.algafood.api.v1.assembler.KitchenInputDisassembler;
import com.philippo.algafood.api.v1.assembler.KitchenModelAssembler;
import com.philippo.algafood.api.v1.model.KitchenModel;
import com.philippo.algafood.api.v1.model.input.KitchenInput;
import com.philippo.algafood.api.v1.openapi.controller.KitchenControllerOpenApi;
import com.philippo.algafood.api.v2.assembler.KitchenInputDisassemblerV2;
import com.philippo.algafood.api.v2.assembler.KitchenModelAssemblerV2;
import com.philippo.algafood.api.v2.model.KitchenModelV2;
import com.philippo.algafood.api.v2.model.input.KitchenInputV2;
import com.philippo.algafood.api.v2.openapi.controller.KitchenControllerV2OpenApi;
import com.philippo.algafood.domain.model.Kitchen;
import com.philippo.algafood.domain.repository.KitchenRepository;
import com.philippo.algafood.domain.service.RegisterKitchenService;
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

@RestController
@RequestMapping(value = "/v2/kitchens", produces = MediaType.APPLICATION_JSON_VALUE)
public class KitchenControllerV2 implements KitchenControllerV2OpenApi {

	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private RegisterKitchenService registerKitchen;

	@Autowired
	private KitchenModelAssemblerV2 kitchenModelAssembler;

	@Autowired
	private KitchenInputDisassemblerV2 kitchenInputDisassembler;

	@Autowired
	private PagedResourcesAssembler<Kitchen> pagedResourcesAssembler;

	@GetMapping
	public PagedModel<KitchenModelV2> list(@PageableDefault(size  = 10) Pageable pageable){
		Page<Kitchen> kitchenPages = kitchenRepository.findAll(pageable);

		PagedModel<KitchenModelV2> kitchensPagedModel = pagedResourcesAssembler.toModel(kitchenPages, kitchenModelAssembler);

		return kitchensPagedModel;
	}
	
	@GetMapping("/{kitchenId}")
	public KitchenModelV2 find(@PathVariable Long kitchenId) {
		Kitchen kitchen = registerKitchen.findOrFail(kitchenId);

		return kitchenModelAssembler.toModel(kitchen);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public KitchenModelV2 add(@RequestBody @Valid KitchenInputV2 kitchenInput) {
		Kitchen kitchen = kitchenInputDisassembler.toDomainObject(kitchenInput);
		return kitchenModelAssembler.toModel(registerKitchen.save(kitchen));
	}
	
	@PutMapping("/{kitchenId}")
	public KitchenModelV2 update(@PathVariable Long kitchenId, @RequestBody @Valid KitchenInputV2 kitchenInput){
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
