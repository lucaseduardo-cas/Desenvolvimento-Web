package com.br.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordem_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    private String descricaoServico;
    private LocalDate dataManutencao;
    private double valorPecas;
    private double valorMaoDeObra;
    private String nomeProfissional;

    // Orientação a Objetos: Uma Ordem de Serviço pode conter várias peças instaladas
    @ManyToMany
    @JoinTable(
        name = "os_pecas",
        joinColumns = @JoinColumn(name = "os_id"),
        inverseJoinColumns = @JoinColumn(name = "peca_id")
    )
    private List<Peca> pecasUtilizadas = new ArrayList<>();

    // Construtores
    public OrdemServico() {}

    // Método de Negócio OO para calcular o valor total da O.S.
    public double getValorTotal() {
        return this.valorPecas + this.valorMaoDeObra;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }

    public String getDescricaoServico() { return descricaoServico; }
    public void setDescricaoServico(String descricaoServico) { this.descricaoServico = descricaoServico; }

    public LocalDate getDataManutencao() { return dataManutencao; }
    public void setDataManutencao(LocalDate dataManutencao) { this.dataManutencao = dataManutencao; }

    public double getValorPecas() { return valorPecas; }
    public void setValorPecas(double valorPecas) { this.valorPecas = valorPecas; }

    public double getValorMaoDeObra() { return valorMaoDeObra; }
    public void setValorMaoDeObra(double valorMaoDeObra) { this.valorMaoDeObra = valorMaoDeObra; }

    public String getNomeProfissional() { return nomeProfissional; }
    public void setNomeProfissional(String nomeProfissional) { this.nomeProfissional = nomeProfissional; }

    public List<Peca> getPecasUtilizadas() { return pecasUtilizadas; }
    public void setPecasUtilizadas(List<Peca> pecasUtilizadas) { this.pecasUtilizadas = pecasUtilizadas; }
}