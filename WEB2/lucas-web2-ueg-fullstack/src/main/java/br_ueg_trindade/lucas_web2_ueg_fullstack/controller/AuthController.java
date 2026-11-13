package br_ueg_trindade.lucas_web2_ueg_fullstack.controller;

import br_ueg_trindade.lucas_web2_ueg_fullstack.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(authService.login(request.username(), request.senha()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of("erro", "Usuário ou senha inválidos"));
        }
    }

    public record LoginRequest(String username, String senha) {}

    public record LoginResposta(String token, String nome, List<String> permissoes) {}
}
