package br_ueg_trindade.lucas_web2_ueg_fullstack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Usuario;
import br_ueg_trindade.lucas_web2_ueg_fullstack.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }

    public Usuario criar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario dadosNovos) {
        Usuario usuario = buscarPorId(id);
        usuario.setNome(dadosNovos.getNome());
        usuario.setUsername(dadosNovos.getUsername());
        usuario.setEmail(dadosNovos.getEmail());
        if (dadosNovos.getSenha() != null && !dadosNovos.getSenha().isBlank()) {
            usuario.setSenha(dadosNovos.getSenha());
        }
        return usuarioRepository.save(usuario);
    }

    public void excluir(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }
}