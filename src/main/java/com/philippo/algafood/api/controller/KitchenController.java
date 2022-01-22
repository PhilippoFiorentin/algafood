package com.philippo.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import com.philippo.algafood.domain.model.Kitchen;
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
import com.philippo.algafood.domain.repository.KitchenRepository;
import com.philippo.algafood.domain.service.RegisterKitchenService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private RegisterKitchenService registerKitchen;
	
	@GetMapping
	public List<Kitchen> listAllKitchens(){
		return kitchenRepository.findAll();
	}
	
	@GetMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> findKitchen(@PathVariable Long kitchenId) {
		Optional<Kitchen> kitchen = kitchenRepository.findById(kitchenId);

		if(kitchen.isPresent())
			return ResponseEntity.ok(kitchen.get());

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen addKitchen(@RequestBody Kitchen kitchen) {
		return registerKitchen.save(kitchen);
	}
	
	@PutMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId, @RequestBody
        Kitchen kitchen){
		Kitchen currentKitchen = kitchenRepository
			.findById(kitchenId)
			.orElse(null);
		
		if (currentKitchen != null) {
			BeanUtils.copyProperties(kitchen, currentKitchen, "id");

			Kitchen savedKitchen = registerKitchen.save(currentKitchen);
			return ResponseEntity.ok(savedKitchen);
		}
		
		return ResponseEntity.notFound().build();
	}
	
//	@DeleteMapping("/{kitchenId}")
//	public ResponseEntity<Kitchen> delete(@PathVariable Long kitchenId){
//
//		try {
//			registerKitchen.delete(kitchenId);
//
//			return ResponseEntity.noContent().build();
//
//		} catch (EntityNotFoundException e) {
//			return ResponseEntity.notFound().build();
//
//		} catch (EntityInUseException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).build();
//		}
//	}

	@DeleteMapping("/{kitchenId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long kitchenId){
		try{
			registerKitchen.delete(kitchenId);
		}catch (EntityNotFoundException e){
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
