package br_ueg_trindade.lucas_web2_ueg_fullstack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/permissoes")
@CrossOrigin(origins = "http://localhost:5173")
public class PermissaoController {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @GetMapping
    public List<Permissao> getAllPermissoes() {
        return permissaoRepository.findAll();
    }

    @PostMapping
    public Permissao createPermissao(@RequestBody Permissao permissao) {
        return permissaoRepository.save(permissao);
    }

    @PutMapping("/{id}")
    public Permissao updatePermissao(@PathVariable Long id, @RequestBody Permissao dadosNovos) {
        Permissao permissao = permissaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permissão não encontrada"));
        permissao.setNome(dadosNovos.getNome());
        permissao.setDescricao(dadosNovos.getDescricao());
        return permissaoRepository.save(permissao);
    }

    @DeleteMapping("/{id}")
    public void deletePermissao(@PathVariable Long id) {
        permissaoRepository.deleteById(id);
    }
}