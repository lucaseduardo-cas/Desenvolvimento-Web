package br_ueg_trindade.lucas_web2_ueg_fullstack.repository;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    Optional<Carro> findByPlaca(String placa);
}