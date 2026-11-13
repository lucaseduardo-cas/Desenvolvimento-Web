package br_ueg_trindade.lucas_web2_ueg_fullstack.controller;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Usuario;
import br_ueg_trindade.lucas_web2_ueg_fullstack.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.criar(usuario);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioService.atualizar(id, usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.excluir(id);
    }

    @GetMapping("/{id}/permissoes")
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario getPermissoes(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/{id}/permissoes")
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario atualizarPermissoes(
            @PathVariable Long id,
            @RequestBody List<Long> idsPermissoes) {
        return usuarioService.atribuirPermissoes(id, idsPermissoes);
    }
}