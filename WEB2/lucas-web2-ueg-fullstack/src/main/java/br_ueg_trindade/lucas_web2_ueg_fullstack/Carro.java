package br_ueg_trindade.lucas_web2_ueg_fullstack;

public class Carro {
    private Long id;
    private String modelo;
    private String marca;
    private Integer ano;

    public Carro() {}

    public Carro(Long id, String modelo, String marca, Integer ano) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
}