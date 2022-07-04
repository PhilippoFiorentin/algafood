package com.philippo.algafood.api.controller;

import java.util.List;

import com.philippo.algafood.domain.model.Kitchen;
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

import com.philippo.algafood.domain.repository.KitchenRepository;
import com.philippo.algafood.domain.service.RegisterKitchenService;

import javax.validation.Valid;

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
	public Kitchen findKitchen(@PathVariable Long kitchenId) {
		return registerKitchen.findOrFail(kitchenId);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen addKitchen(@RequestBody @Valid Kitchen kitchen) {
		return registerKitchen.save(kitchen);
	}
	
	@PutMapping("/{kitchenId}")
	public Kitchen update(@PathVariable Long kitchenId, @RequestBody @Valid Kitchen kitchen){
		Kitchen currentKitchen = registerKitchen.findOrFail(kitchenId);
		
		BeanUtils.copyProperties(kitchen, currentKitchen, "id");

		return registerKitchen.save(currentKitchen);
	}

	@DeleteMapping("/{kitchenId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long kitchenId){
		registerKitchen.delete(kitchenId);
	}
}
