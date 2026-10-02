package br_ueg_trindade.lucas_web2_ueg_fullstack.service;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Carro;
import br_ueg_trindade.lucas_web2_ueg_fullstack.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> listarTodos() {
        return carroRepository.findAll();
    }

    public Carro buscarPorId(Long id) {
        return carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado com id: " + id));
    }

    public Carro criar(Carro carro) {
        String placaFormatada = carro.getPlaca().trim().toUpperCase();
        Optional<Carro> existente = carroRepository.findByPlaca(placaFormatada);
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Já existe um carro cadastrado com a placa: " + placaFormatada);
        }
        carro.setPlaca(placaFormatada);
        return carroRepository.save(carro);
    }

    public Carro atualizar(Long id, Carro dadosNovos) {
        Carro carro = buscarPorId(id);
        String placaFormatada = dadosNovos.getPlaca().trim().toUpperCase();

        Optional<Carro> existente = carroRepository.findByPlaca(placaFormatada);
        if (existente.isPresent() && !existente.get().getId().equals(id)) {
            throw new IllegalArgumentException("A placa informada já pertence a outro veículo.");
        }

        carro.setMarca(dadosNovos.getMarca());
        carro.setModelo(dadosNovos.getModelo());
        carro.setAno(dadosNovos.getAno());
        carro.setPlaca(placaFormatada);
        return carroRepository.save(carro);
    }

    public void excluir(Long id) {
        carroRepository.deleteById(id);
    }
}