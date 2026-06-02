package com.br.model;

import jakarta.persistence.*;

@Entity
@Table(name = "peca")
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoPeca;
    private String nome;
    private String modeloCompativel;
    private String anoCompativel;
    private String outrasMarcasCompatíveis;

    // Construtores
    public Peca() {}

    public Peca(Long id, String codigoPeca, String nome, String modeloCompativel, String anoCompativel, String outrasMarcasCompatíveis) {
        this.id = id;
        this.codigoPeca = codigoPeca;
        this.nome = nome;
        this.modeloCompativel = modeloCompativel;
        this.anoCompativel = anoCompativel;
        this.outrasMarcasCompatíveis = outrasMarcasCompatíveis;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoPeca() { return codigoPeca; }
    public void setCodigoPeca(String codigoPeca) { this.codigoPeca = codigoPeca; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getModeloCompativel() { return modeloCompativel; }
    public void setModeloCompativel(String modeloCompativel) { this.modeloCompativel = modeloCompativel; }

    public String getAnoCompativel() { return anoCompativel; }
    public void setAnoCompativel(String anoCompativel) { this.anoCompativel = anoCompativel; }

    public String getOutrasMarcasCompatíveis() { return outrasMarcasCompatíveis; }
    public void setOutrasMarcasCompatíveis(String outrasMarcasCompatíveis) { this.outrasMarcasCompatíveis = outrasMarcasCompatíveis; }
}