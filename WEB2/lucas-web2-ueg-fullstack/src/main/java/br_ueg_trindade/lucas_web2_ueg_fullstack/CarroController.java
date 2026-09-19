package br_ueg_trindade.lucas_web2_ueg_fullstack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/carros")
@CrossOrigin(origins = "http://localhost:5173")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @GetMapping
    public List<Carro> getAllCarros() {
        return carroRepository.findAll();
    }

    @PostMapping
    public Carro createCarro(@RequestBody Carro carro) {
        return carroRepository.save(carro);
    }

    @PutMapping("/{id}")
    public Carro updateCarro(@PathVariable Long id, @RequestBody Carro dadosNovos) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
        carro.setMarca(dadosNovos.getMarca());
        carro.setModelo(dadosNovos.getModelo());
        carro.setAno(dadosNovos.getAno());
        carro.setPlaca(dadosNovos.getPlaca());
        return carroRepository.save(carro);
    }

    @DeleteMapping("/{id}")
    public void deleteCarro(@PathVariable Long id) {
        carroRepository.deleteById(id);
    }
}