package br_ueg_trindade.lucas_web2_ueg_fullstack.controller;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Carro;
import br_ueg_trindade.lucas_web2_ueg_fullstack.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/carros")
@CrossOrigin(origins = "http://localhost:5173")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public List<Carro> getAllCarros() {
        return carroService.listarTodos();
    }

    @GetMapping("/{id}")
    public Carro getCarroById(@PathVariable Long id) {
        return carroService.buscarPorId(id);
    }

    @PostMapping
    public Carro createCarro(@RequestBody Carro carro) {
        return carroService.criar(carro);
    }

    @PutMapping("/{id}")
    public Carro updateCarro(@PathVariable Long id, @RequestBody Carro dadosNovos) {
        return carroService.atualizar(id, dadosNovos);
    }

    @DeleteMapping("/{id}")
    public void deleteCarro(@PathVariable Long id) {
        carroService.excluir(id);
    }
}