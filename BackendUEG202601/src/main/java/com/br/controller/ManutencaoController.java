package com.br.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.br.model.Manutencao;
import com.br.repository.ManutencaoRepository;
import com.br.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/cmanutencao") // Rota principal
@CrossOrigin(origins = "*")
public class ManutencaoController {

	@Autowired
	private ManutencaoRepository mrep;

	// 1. LISTAR
	@GetMapping("/manutencao")
	public List<Manutencao> listar() {
		return mrep.findAll();
	}

	// 2. CONSULTAR (por ID)
	@GetMapping("/manutencao/{id}")
	public ResponseEntity<Manutencao> consultar(@PathVariable Long id) {
		Manutencao m = mrep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro: Registro \" + id + \" não existe no banco"));
		return ResponseEntity.ok(m);
	}

	// 3. INCLUIR
	@PostMapping("/manutencao")
	public Manutencao salvar(@RequestBody Manutencao obj) {
	    return mrep.save(obj);
	}

	// 4. ALTERAR
	@PutMapping("/manutencao/{id}")
	public ResponseEntity<Manutencao> atualizar(@PathVariable Long id, @RequestBody Manutencao dados) {
	    Manutencao m = mrep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao alterar"));
	    m.setDescricaoServico(dados.getDescricaoServico());
	    m.setValorTotal(dados.getValorTotal());
	    m.setDataRealizacao(dados.getDataRealizacao());
	    m.setQuilometragem(dados.getQuilometragem());
	    m.setPecasTrocadas(dados.getPecasTrocadas());
	    
	    return ResponseEntity.ok(mrep.save(m));
	}

	// 5. EXCLUIR
	@DeleteMapping("/manutencao/{id}")
	public Map<String, Boolean> excluir(@PathVariable Long id) {
		Manutencao m = mrep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao deletar"));
		mrep.delete(m);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deletado", Boolean.TRUE);
		return response;
	}
}