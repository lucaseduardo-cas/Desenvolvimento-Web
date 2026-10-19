package br_ueg_trindade.lucas_web2_ueg_fullstack.service;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Permissao;
import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Usuario;
import br_ueg_trindade.lucas_web2_ueg_fullstack.repository.PermissaoRepository;
import br_ueg_trindade.lucas_web2_ueg_fullstack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void excluir(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public Usuario atribuirPermissoes(Long usuarioId, List<Long> idsPermissoes) {
        Usuario usuario = buscarPorId(usuarioId);
        Set<Permissao> permissoes = new HashSet<>(permissaoRepository.findAllById(idsPermissoes));
        usuario.setPermissoes(permissoes);
        return usuarioRepository.save(usuario);
    }

    public Usuario criar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Long id, Usuario dadosAtualizados) {
        Usuario usuario = buscarPorId(id);
        usuario.setNome(dadosAtualizados.getNome());
        usuario.setUsername(dadosAtualizados.getUsername());
        usuario.setEmail(dadosAtualizados.getEmail());
        if (dadosAtualizados.getSenha() != null && !dadosAtualizados.getSenha().isBlank()) {
            usuario.setSenha(dadosAtualizados.getSenha());
        }
        return usuarioRepository.save(usuario);
    }
}