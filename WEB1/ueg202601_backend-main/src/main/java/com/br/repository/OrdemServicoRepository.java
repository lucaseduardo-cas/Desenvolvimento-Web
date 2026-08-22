package com.br.repository;

import com.br.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    // Busca todas as Ordens de Serviço vinculadas a um veículo específico pelo ID dele
    List<OrdemServico> findByVeiculoId(Long veiculoId);
}