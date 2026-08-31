package br_ueg_trindade.lucas_web2_ueg_fullstack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    @GetMapping
    public List<Permissao> getAllPermissoes() {
        List<Permissao> permissoes = new ArrayList<>();
        permissoes.add(new Permissao(1L, "ROLE_ADMIN", "Acesso total ao sistema"));
        permissoes.add(new Permissao(2L, "ROLE_USER", "Acesso restrito a consultas"));
        return permissoes;
    }
}