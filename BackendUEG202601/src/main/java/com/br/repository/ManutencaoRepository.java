package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.model.Manutencao;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
}