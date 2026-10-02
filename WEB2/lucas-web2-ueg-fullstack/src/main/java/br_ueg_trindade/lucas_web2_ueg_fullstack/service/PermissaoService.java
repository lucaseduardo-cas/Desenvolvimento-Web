package br_ueg_trindade.lucas_web2_ueg_fullstack.service;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Permissao;
import br_ueg_trindade.lucas_web2_ueg_fullstack.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public List<Permissao> listarTodos() {
        return permissaoRepository.findAll();
    }

    public Permissao buscarPorId(Long id) {
        return permissaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permissão não encontrada com id: " + id));
    }

    public Permissao criar(Permissao permissao) {
        return permissaoRepository.save(permissao);
    }

    public Permissao atualizar(Long id, Permissao dadosNovos) {
        Permissao permissao = buscarPorId(id);
        permissao.setNome(dadosNovos.getNome());
        permissao.setDescricao(dadosNovos.getDescricao());
        return permissaoRepository.save(permissao);
    }

    public void excluir(Long id) {
        permissaoRepository.deleteById(id);
    }
}