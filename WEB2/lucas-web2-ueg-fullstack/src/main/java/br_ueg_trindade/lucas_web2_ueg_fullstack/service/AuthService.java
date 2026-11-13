package br_ueg_trindade.lucas_web2_ueg_fullstack.service;

import br_ueg_trindade.lucas_web2_ueg_fullstack.controller.AuthController.LoginResposta;
import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Permissao;
import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Usuario;
import br_ueg_trindade.lucas_web2_ueg_fullstack.repository.UsuarioRepository;
import br_ueg_trindade.lucas_web2_ueg_fullstack.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthService {

    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public LoginResposta login(String username, String senha) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

        if (senha == null || usuario.getSenha() == null
                || !passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        List<String> permissoes = usuario.getPermissoes().stream()
                .map(Permissao::getNome)
                .toList();

        return new LoginResposta(jwtUtil.gerarToken(usuario), usuario.getNome(), permissoes);
    }
}
