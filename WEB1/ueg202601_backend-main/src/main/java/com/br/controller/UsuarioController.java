package com.br.controller;

import com.br.model.Usuario;
import com.br.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Rota para Cadastrar Novo Usuário
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody Usuario usuario) {
        if (usuarioRepository.findByLogin(usuario.getLogin()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este login já existe!");
        }
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário cadastrado com sucesso!");
    }

    // Rota Simples de Autenticação (Login)
    @PostMapping("/login")
    public ResponseEntity<String> autenticar(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioBanco = usuarioRepository.findByLogin(usuario.getLogin());
        
        if (usuarioBanco.isPresent() && usuarioBanco.get().getSenha().equals(usuario.getSenha())) {
            return ResponseEntity.ok("Sucesso");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos!");
    }
}