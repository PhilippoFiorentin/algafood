package com.philippo.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import com.philippo.algafood.domain.model.State;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.philippo.algafood.domain.exception.EntityInUseException;
import com.philippo.algafood.domain.exception.EntityNotFoundException;
import com.philippo.algafood.domain.repository.StateRepository;
import com.philippo.algafood.domain.service.RegisterStateService;

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
	public ResponseEntity<State> findState(@PathVariable Long stateId){
		Optional<State> state = stateRepository.findById(stateId);

		if (state.isPresent()) {
			return ResponseEntity.ok(state.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public State save(@RequestBody State state){
		return registerState.save(state);
	}
	
	@PutMapping("/{stateId}")
	public ResponseEntity<State> update(@PathVariable Long stateId, @RequestBody State state){
		State currentState = stateRepository
			.findById(stateId)
			.orElse(null);
		
		if (currentState != null) {
			BeanUtils.copyProperties(state, currentState, "id");

			State savedState = registerState.save(currentState);
			return ResponseEntity.ok(savedState);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{stateId}")
	public ResponseEntity<?> delete(@PathVariable Long stateId){
		try {
			registerState.delete(stateId);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
