package br_ueg_trindade.lucas_web2_ueg_fullstack.repository;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    Optional<Permissao> findByNome(String nome);
}
