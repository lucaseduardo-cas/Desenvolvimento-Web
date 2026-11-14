package com.br.repository;

import com.br.model.Peca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PecaRepository extends JpaRepository<Peca, Long> {
}