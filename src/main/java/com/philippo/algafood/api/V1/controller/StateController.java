package com.philippo.algafood.api.V1.controller;

import com.philippo.algafood.api.V1.assembler.StateInputDisassembler;
import com.philippo.algafood.api.V1.assembler.StateModelAssembler;
import com.philippo.algafood.api.V1.model.StateModel;
import com.philippo.algafood.api.V1.model.input.StateInput;
import com.philippo.algafood.api.V1.openapi.controller.StateControllerOpenApi;
import com.philippo.algafood.core.security.CheckSecurity;
import com.philippo.algafood.domain.model.State;
import com.philippo.algafood.domain.repository.StateRepository;
import com.philippo.algafood.domain.service.RegisterStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/v1/states", produces = MediaType.APPLICATION_JSON_VALUE)
public class StateController implements StateControllerOpenApi {
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private RegisterStateService registerState;

	@Autowired
	private StateModelAssembler stateModelAssembler;

	@Autowired
	private StateInputDisassembler stateInputDisassembler;

	@CheckSecurity.States.CanConsult
	@GetMapping
	public CollectionModel<StateModel> list(){
		return stateModelAssembler.toCollectionModel(stateRepository.findAll());
	}

	@CheckSecurity.States.CanConsult
	@GetMapping("/{stateId}")
	public StateModel find(@PathVariable Long stateId){
		State state = registerState.findOrFail(stateId);

		return stateModelAssembler.toModel(state);
	}

	@CheckSecurity.States.CanEdit
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public StateModel add(@RequestBody @Valid StateInput stateInput){
		State state = stateInputDisassembler.toDomainObject(stateInput);

		return stateModelAssembler.toModel(registerState.save(state));
	}

	@CheckSecurity.States.CanEdit
	@PutMapping("/{stateId}")
	public StateModel update(@PathVariable Long stateId, @RequestBody @Valid StateInput stateInput){
		State currentState = registerState.findOrFail(stateId);

		stateInputDisassembler.copyToDomainObject(stateInput, currentState);

		currentState =  registerState.save(currentState);

		return stateModelAssembler.toModel(currentState);
	}

	@CheckSecurity.States.CanEdit
	@DeleteMapping("/{stateId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long stateId){
		registerState.delete(stateId);
	}
}
