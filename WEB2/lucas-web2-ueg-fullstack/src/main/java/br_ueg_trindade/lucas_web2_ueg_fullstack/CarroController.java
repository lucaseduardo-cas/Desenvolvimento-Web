package br_ueg_trindade.lucas_web2_ueg_fullstack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @GetMapping
    public List<Carro> getAllCarros() {
        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro(1L, "Civic EX", "Honda", 2004));
        carros.add(new Carro(2L, "Saveiro", "Volkswagen", 2016));
        return carros;
    }
}