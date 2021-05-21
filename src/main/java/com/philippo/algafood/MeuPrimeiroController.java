package com.philippo.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.philippo.algafood.di.modelo.Cliente;
import com.philippo.algafood.di.service.AtivacaoClienteService;

@Controller
public class MeuPrimeiroController {
	
	private AtivacaoClienteService ativacaoClienteService;
	
	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;
		
		System.out.println("MeuPrimeiroController: " + ativacaoClienteService);
	}

	@GetMapping("/hello")
	@ResponseBody
	public String Hello() {
		
		Cliente maria = new Cliente("Maria", "maria@email.com", "33333333");
		ativacaoClienteService.ativar(maria);
		return "Hello!";
	}
}
