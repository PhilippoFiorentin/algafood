package com.philippo.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.philippo.algafood.AlgafoodApiApplication;
import com.philippo.algafood.domain.model.Permissao;
import com.philippo.algafood.domain.repository.PermissaoRepository;

public class ConsultaFormaPagamentoMain {
	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);
		
		List<Permissao> todasPermissoes = permissaoRepository.todas(); 
		
		for(Permissao permissao: todasPermissoes)
			System.out.printf("%s - %s\n", permissao.getNome(), permissao.getDescricao());
	}
}

	
