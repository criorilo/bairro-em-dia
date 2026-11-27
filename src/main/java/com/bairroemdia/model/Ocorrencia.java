
package com.bairroemdia.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_OCORRENCIA")
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(nullable = false, length = 200)
    private String endereco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOcorrencia status;

    @Column(nullable = false)
    private LocalDateTime dataRegistro;

    @ManyToOne
    @JoinColumn(name = "morador_id", nullable = false)
    private Morador morador;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Ocorrencia() {
    }

    public Ocorrencia(String titulo, String descricao,
                      String endereco, Morador morador,
                      Categoria categoria) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.endereco = endereco;
        this.morador = morador;
        this.categoria = categoria;
        this.status = StatusOcorrencia.ABERTA;
        this.dataRegistro = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public StatusOcorrencia getStatus() {
        return status;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public Morador getMorador() {
        return morador;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void atualizar(String titulo, String descricao,
                          String endereco) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.endereco = endereco;
    }

    public void atualizarStatus(StatusOcorrencia novoStatus) {
        this.status = novoStatus;
    }
}
