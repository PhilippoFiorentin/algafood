package com.philippo.algafood.api.controller;

import java.util.List;

import com.philippo.algafood.domain.model.State;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.philippo.algafood.domain.repository.StateRepository;
import com.philippo.algafood.domain.service.RegisterStateService;

import javax.validation.Valid;

@RestController
@RequestMapping("/states")
public class StateController {
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private RegisterStateService registerState;
	
	@GetMapping
	public List<State> listAllStates(){
		return stateRepository.findAll();
	}
	
	@GetMapping("/{stateId}")
	public State findState(@PathVariable Long stateId){
		return registerState.findOrFail(stateId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public State save(@RequestBody @Valid State state){
		return registerState.save(state);
	}
	
	@PutMapping("/{stateId}")
	public State update(@PathVariable Long stateId, @RequestBody @Valid State state){
		State currentState = registerState.findOrFail(stateId);

		BeanUtils.copyProperties(state, currentState, "id");

		return registerState.save(currentState);
	}
	
	@DeleteMapping("/{stateId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long stateId){
		registerState.delete(stateId);
	}
}
