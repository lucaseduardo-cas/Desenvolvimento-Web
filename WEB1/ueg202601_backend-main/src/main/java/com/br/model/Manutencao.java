package com.br.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "manutencao")
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "peca_id")
    private Peca peca;

    private LocalDate dataManutencao;
    private LocalDate proximaManutencao;
    private String tipo; // "Preventiva" ou "Corretiva"
    
    @Transient // Indica ao Hibernate para não criar esta coluna no banco, pois ela é calculada em tempo de execução
    private String statusAlerta; 

    // Construtores
    public Manutencao() {}

    public Manutencao(Long id, Veiculo veiculo, Peca peca, LocalDate dataManutencao, LocalDate proximaManutencao, String tipo) {
        this.id = id;
        this.veiculo = veiculo;
        this.peca = peca;
        this.dataManutencao = dataManutencao;
        this.proximaManutencao = proximaManutencao;
        this.tipo = tipo;
    }

    // Lógica Orientada a Objetos para definir o status da preventiva baseado na data atual (2026)
    public String getStatusAlerta() {
        if (this.proximaManutencao == null) return "Sem previsão";
        
        LocalDate hoje = LocalDate.now();
        if (hoje.isAfter(this.proximaManutencao)) {
            return "Passou da hora de reparar!";
        } else if (hoje.plusDays(30).isAfter(this.proximaManutencao)) {
            return "Próximo da hora de reparar";
        } else {
            return "Manutenção em dia (OK)";
        }
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }

    public Peca getPeca() { return peca; }
    public void setPeca(Peca peca) { this.peca = peca; }

    public LocalDate getDataManutencao() { return dataManutencao; }
    public void setDataManutencao(LocalDate dataManutencao) { this.dataManutencao = dataManutencao; }

    public LocalDate getProximaManutencao() { return proximaManutencao; }
    public void setProximaManutencao(LocalDate proximaManutencao) { this.proximaManutencao = proximaManutencao; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}