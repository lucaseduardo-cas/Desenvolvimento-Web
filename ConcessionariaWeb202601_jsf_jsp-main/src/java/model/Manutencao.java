package model;

import java.time.LocalDate;

public class Manutencao {
    private Long id;
    private String descricaoServico;
    private LocalDate dataRealizacao;
    private Double valorTotal;
    private Integer quilometragem;
    private Boolean pecasTrocadas;
    private Automovel automovel;

    public Manutencao() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricaoServico() { return descricaoServico; }
    public void setDescricaoServico(String descricaoServico) { this.descricaoServico = descricaoServico; }
    public LocalDate getDataRealizacao() { return dataRealizacao; }
    public void setDataRealizacao(LocalDate dataRealizacao) { this.dataRealizacao = dataRealizacao; }
    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }
    public Integer getQuilometragem() { return quilometragem; }
    public void setQuilometragem(Integer quilometragem) { this.quilometragem = quilometragem; }
    public Boolean getPecasTrocadas() { return pecasTrocadas; }
    public void setPecasTrocadas(Boolean pecasTrocadas) { this.pecasTrocadas = pecasTrocadas; }
    public Automovel getAutomovel() { return automovel; }
    public void setAutomovel(Automovel automovel) { this.automovel = automovel; }
}