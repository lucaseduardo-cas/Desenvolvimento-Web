package com.br.repository;

import com.br.model.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
    // Método customizado do Spring Data para buscar o histórico de um veículo específico pelo ID dele
    List<Manutencao> findByVeiculoId(Long veiculoId);
}