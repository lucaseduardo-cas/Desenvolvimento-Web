package br_ueg_trindade.lucas_web2_ueg_fullstack.config;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Permissao;
import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Usuario;
import br_ueg_trindade.lucas_web2_ueg_fullstack.repository.PermissaoRepository;
import br_ueg_trindade.lucas_web2_ueg_fullstack.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConfiguracaoInicial {

    @Bean
    CommandLineRunner carregarDados(UsuarioRepository usuarios,
                                    PermissaoRepository permissoes,
                                    PasswordEncoder encoder) {
        return args -> {
            Permissao admin = permissoes.findByNome("ADMIN")
                    .orElseGet(() -> permissoes.save(new Permissao("ADMIN", "Acesso total")));
            Permissao user = permissoes.findByNome("USER")
                    .orElseGet(() -> permissoes.save(new Permissao("USER", "Acesso comum")));

            if (usuarios.findByUsername("admin").isEmpty()) {
                Usuario u1 = new Usuario("Admin", "admin", "admin@ueg.br", encoder.encode("admin123"));
                u1.setPermissoes(new HashSet<>(Set.of(admin, user)));
                usuarios.save(u1);
            }

            if (usuarios.findByUsername("joao").isEmpty()) {
                Usuario u2 = new Usuario("João", "joao", "joao@ueg.br", encoder.encode("joao123"));
                u2.setPermissoes(new HashSet<>(Set.of(user)));
                usuarios.save(u2);
            }
        };
    }
}
