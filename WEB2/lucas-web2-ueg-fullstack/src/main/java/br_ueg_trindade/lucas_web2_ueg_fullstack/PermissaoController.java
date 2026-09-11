package br_ueg_trindade.lucas_web2_ueg_fullstack;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    private final PermissaoRepository permissaoRepository;

    public PermissaoController(PermissaoRepository permissaoRepository) {
        this.permissaoRepository = permissaoRepository;
    }

    @GetMapping
    public List<Permissao> getAllPermissoes() {
        return permissaoRepository.findAll();
    }

    @PostMapping
    public Permissao createPermissao(@RequestBody Permissao permissao) {
        return permissaoRepository.save(permissao);
    }

    @GetMapping("/{id}")
    public Permissao getPermissaoById(@PathVariable Long id) {
        return permissaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permissão não encontrada"));
    }
}