package com.br.repository;

import com.br.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método mágico do Spring Data para buscar o usuário direto pelo login dele
    Optional<Usuario> findByLogin(String login);
}