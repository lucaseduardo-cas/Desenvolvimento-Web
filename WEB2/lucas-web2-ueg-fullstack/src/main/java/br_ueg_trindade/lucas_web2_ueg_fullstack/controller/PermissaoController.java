package br_ueg_trindade.lucas_web2_ueg_fullstack.controller;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Permissao;
import br_ueg_trindade.lucas_web2_ueg_fullstack.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/permissoes")
@CrossOrigin(origins = "http://localhost:5173")
public class PermissaoController {

    @Autowired
    private PermissaoService permissaoService;

    @GetMapping
    public List<Permissao> getAllPermissoes() {
        return permissaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Permissao getPermissaoById(@PathVariable Long id) {
        return permissaoService.buscarPorId(id);
    }

    @PostMapping
    public Permissao createPermissao(@RequestBody Permissao permissao) {
        return permissaoService.criar(permissao);
    }

    @PutMapping("/{id}")
    public Permissao updatePermissao(@PathVariable Long id, @RequestBody Permissao dadosNovos) {
        return permissaoService.atualizar(id, dadosNovos);
    }

    @DeleteMapping("/{id}")
    public void deletePermissao(@PathVariable Long id) {
        permissaoService.excluir(id);
    }
}