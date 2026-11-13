package br_ueg_trindade.lucas_web2_ueg_fullstack.controller;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Permissao;
import br_ueg_trindade.lucas_web2_ueg_fullstack.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/permissoes")
@CrossOrigin(origins = "http://localhost:5173")
public class PermissaoController {

    @Autowired
    private PermissaoService permissaoService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Permissao> getAllPermissoes() {
        return permissaoService.listarTodos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Permissao getPermissaoById(@PathVariable Long id) {
        return permissaoService.buscarPorId(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Permissao createPermissao(@RequestBody Permissao permissao) {
        return permissaoService.criar(permissao);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Permissao updatePermissao(@PathVariable Long id, @RequestBody Permissao dadosNovos) {
        return permissaoService.atualizar(id, dadosNovos);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePermissao(@PathVariable Long id) {
        permissaoService.excluir(id);
    }
}