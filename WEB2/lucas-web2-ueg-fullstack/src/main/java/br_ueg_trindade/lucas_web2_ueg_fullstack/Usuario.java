package br_ueg_trindade.lucas_web2_ueg_fullstack;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // 1. Diz ao Spring/Hibernate que esta classe representa uma tabela no banco
public class Usuario {

    @Id // 2. Define que este atributo é a chave primária (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 3. Auto-incremento gerenciado pelo banco
    private Long id;

    private String nome;
    private String username;

    @JsonIgnore // 4. Oculta o campo de senha no retorno JSON por segurança
    private String senha;

    private String email;

    // Construtor padrão sem argumentos (obrigatório pelo JPA/Hibernate)
    public Usuario() {}

    // Construtor completo para facilitar instanciação
    public Usuario(String nome, String username, String senha, String email) {
        this.nome = nome;
        this.username = username;
        this.senha = senha;
        this.email = email;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}