package br_ueg_trindade.lucas_web2_ueg_fullstack.repository;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}