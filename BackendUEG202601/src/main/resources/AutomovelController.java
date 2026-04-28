package com.br.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.br.model.Automovel;
import com.br.repository.AutomovelRepository;

@RequestMapping("/cautomovel/")
@RestController
@CrossOrigin(origins="*")
public class AutomovelController {

    @Autowired
    private AutomovelRepository arep;

    @GetMapping("/automovel")
    public List<Automovel> listar(){
        return this.arep.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
    }
}