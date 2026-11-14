package com.br.controller;

import com.br.model.Peca;
import com.br.repository.PecaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pecas")
@CrossOrigin(origins = "*")
public class PecaController {

    private final PecaRepository pecaRepository;

    public PecaController(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }

    @GetMapping
    public List<Peca> listarTodas() {
        return pecaRepository.findAll();
    }

    @PostMapping
    public Peca salvar(@RequestBody Peca peca) {
        return pecaRepository.save(peca);
    }
}