package br_ueg_trindade.lucas_web2_ueg_fullstack.repository;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}