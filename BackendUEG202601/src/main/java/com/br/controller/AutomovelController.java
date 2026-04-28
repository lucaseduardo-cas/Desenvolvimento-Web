package com.br.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.exception.ResourceNotFoundException;
import com.br.model.Automovel;
import com.br.repository.AutomovelRepository;

// Classe para Listar, Consultar e Inserir novos veículos
@RestController
@RequestMapping("/cautomovel/")
@CrossOrigin(origins="*")
public class AutomovelController {

	@Autowired
	private AutomovelRepository repository;

	@GetMapping("/automovel")
	public List<Automovel> listar() {
		return repository.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
	}

	@GetMapping("/automovel/{id}")
	public ResponseEntity<Automovel> consultar(@PathVariable Long id) {
		Automovel auto = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Automovel não encontrado: " + id));
		return ResponseEntity.ok(auto);
	}

	@PostMapping("/automovel")
	public Automovel inserir(@RequestBody Automovel auto) {
		return repository.save(auto);
	}
}