package br_ueg_trindade.lucas_web2_ueg_fullstack;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/carros")
@CrossOrigin(origins = "http://localhost:5173")
public class CarroController {

    private final CarroRepository carroRepository;

    public CarroController(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    @GetMapping
    public List<Carro> getAllCarros() {
        return carroRepository.findAll();
    }

    @PostMapping
    public Carro createCarro(@RequestBody Carro carro) {
        return carroRepository.save(carro);
    }

    @GetMapping("/{id}")
    public Carro getCarroById(@PathVariable Long id) {
        return carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }
}