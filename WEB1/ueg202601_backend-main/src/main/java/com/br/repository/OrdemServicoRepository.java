package com.br.repository;

import com.br.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    // Busca todas as Ordens de Serviço vinculadas a um veículo específico pelo ID dele
    List<OrdemServico> findByVeiculoId(Long veiculoId);
}