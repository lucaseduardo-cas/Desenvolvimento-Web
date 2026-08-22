package com.br.controller;

import com.br.model.Manutencao;
import com.br.repository.ManutencaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/manutencoes")
@CrossOrigin(origins = "*")
public class ManutencaoController {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    @GetMapping
    public List<Manutencao> listarTodas() {
        return manutencaoRepository.findAll();
    }

    @PostMapping
    public Manutencao salvar(@RequestBody Manutencao manutencao) {
        // Regra de Negócio: Se for Preventiva, o sistema calcula a próxima troca para 6 meses depois
        if ("Preventiva".equalsIgnoreCase(manutencao.getTipo()) && manutencao.getDataManutencao() != null) {
            manutencao.setProximaManutencao(manutencao.getDataManutencao().plusMonths(6));
        } else if ("Corretiva".equalsIgnoreCase(manutencao.getTipo()) && manutencao.getDataManutencao() != null) {
            // Se for corretiva de desgaste rápido, prevê para daqui a 3 meses
            manutencao.setProximaManutencao(manutencao.getDataManutencao().plusMonths(3));
        }
        return manutencaoRepository.save(manutencao);
    }

    // Rota solicitada: Pesquisar o histórico de manutenção detalhado de um veículo específico
    @GetMapping("/veiculo/{veiculoId}")
    public List<Manutencao> obterHistoricoVeiculo(@PathVariable Long veiculoId) {
        return manutencaoRepository.findByVeiculoId(veiculoId);
    }
}