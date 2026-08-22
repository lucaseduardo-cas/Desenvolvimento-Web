package com.br.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.exception.ResourceNotFoundException;
import com.br.model.*;
import com.br.repository.AutomovelRepository;

@RestController
@RequestMapping("/cautomovel/")
@CrossOrigin(origins="*")
public class AutomovelController {
	
	//Cria o repositorio JPA para ser usado aqui no controlador
	@Autowired
	private AutomovelRepository autorep;
	
	
	@GetMapping("/automovel")  //Indica que esse será o nome do endereço a ser chamado
	public List<Automovel> listar(){
		
		//para chamar o "listar", o endereço completo deverá ser:
		// http://localhost:8080/cautomovel/automovel -- usando o protocolo http, método GET
		
		return autorep.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
		
	}
	
	@GetMapping("/automovel/{id}")
	public ResponseEntity<Automovel> consultar(@PathVariable Long id) {
		
		Automovel auto = autorep.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Automovel nao encontrado."));
			
		return ResponseEntity.ok(auto);
	}
	
	
	//http://localhost:8080/cautomovel/automovel -- usando o protoloco http, método POST
	@PostMapping("/automovel")
	public Automovel incluir(@RequestBody Automovel automovel) {
		
		return autorep.save(automovel);
		
	}
	
	
	@DeleteMapping("/automovel/{id}")
	public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {
		
		Automovel auto = autorep.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Automovel nao encontrado."));
		
		autorep.delete(auto);
		
		Map<String, Boolean> resposta = new HashMap<>();
		resposta.put("Automovel Excluido!", true);
		return ResponseEntity.ok(resposta);
	}
	
	
	@PutMapping("/automovel/{id}")
	public ResponseEntity<Automovel> alterar(@PathVariable Long id, @RequestBody Automovel automovel) {
		
		Automovel auto = autorep.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Automovel nao encontrado."));
		
		auto.setCodigo(automovel.getCodigo());
		auto.setNome(automovel.getNome());
		auto.setModelo(automovel.getModelo());
		auto.setDataFabricacao(automovel.getDataFabricacao());
		auto.setQuantidade(automovel.getQuantidade());
		auto.setPrecoVenda(automovel.getPrecoVenda());
		auto.setTrioEletrico(automovel.isTrioEletrico());
		
		//Atualizar o objeto Marca, atributo do automóvel
		auto.setMarca(automovel.getMarca());
		
		Automovel atualizado = autorep.save(auto);
			
		return ResponseEntity.ok(atualizado);
	}

}
