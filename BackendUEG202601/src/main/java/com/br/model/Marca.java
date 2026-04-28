package com.br.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name="marca")
public class Marca {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name="nome")
	private String nome;

	public Marca() {
		super();
	}

	// Construtor com todos os atributos
	public Marca(Long codigo, String nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}

	// Gets e Setters 
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
} 
