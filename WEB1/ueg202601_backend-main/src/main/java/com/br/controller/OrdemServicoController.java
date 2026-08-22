package com.br.controller;

import com.br.model.OrdemServico;
import com.br.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ordens-servico")
@CrossOrigin(origins = "*")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoRepository osRepository;

    @GetMapping
    public List<OrdemServico> listarTodas() {
        return osRepository.findAll();
    }

    @PostMapping
    public OrdemServico salvar(@RequestBody OrdemServico os) {
        return osRepository.save(os);
    }

    // Rota solicitada: Trazer o histórico de serviços completo de um carro específico
    @GetMapping("/veiculo/{veiculoId}")
    public List<OrdemServico> obterHistoricoPorVeiculo(@PathVariable Long veiculoId) {
        return osRepository.findByVeiculoId(veiculoId);
    }
}