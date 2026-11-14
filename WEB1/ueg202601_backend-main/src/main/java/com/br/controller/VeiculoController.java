package com.br.controller;

import com.br.model.Veiculo;
import com.br.repository.VeiculoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;

    public VeiculoController(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @GetMapping
    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    @PostMapping
    public Veiculo salvar(@RequestBody Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    @PutMapping("/{id}")
    public Veiculo atualizar(@PathVariable Long id, @RequestBody Veiculo veiculoAtualizado) {
        return veiculoRepository.findById(id).map(veiculo -> {
            veiculo.setPlaca(veiculoAtualizado.getPlaca());
            veiculo.setModelo(veiculoAtualizado.getModelo());
            veiculo.setProprietario(veiculoAtualizado.getProprietario());
            return veiculoRepository.save(veiculo);
        }).orElseGet(() -> {
            veiculoAtualizado.setId(id);
            return veiculoRepository.save(veiculoAtualizado);
        });
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        veiculoRepository.deleteById(id);
    }
}