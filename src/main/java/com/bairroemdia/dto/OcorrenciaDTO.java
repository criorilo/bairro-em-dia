
package com.bairroemdia.dto;

import com.bairroemdia.model.StatusOcorrencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class OcorrenciaDTO {

    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "O endereço é obrigatório")
    private String endereco;

    private StatusOcorrencia status;
    private LocalDateTime dataRegistro;

    @NotNull(message = "O ID do morador é obrigatório")
    private Long moradorId;

    private String nomeMorador;

    @NotNull(message = "O ID da categoria é obrigatório")
    private Long categoriaId;

    private String nomeCategoria;

    public OcorrenciaDTO() {
    }

    public OcorrenciaDTO(Long id, String titulo, String descricao,
                         String endereco, StatusOcorrencia status,
                         LocalDateTime dataRegistro, Long moradorId,
                         String nomeMorador, Long categoriaId,
                         String nomeCategoria) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.endereco = endereco;
        this.status = status;
        this.dataRegistro = dataRegistro;
        this.moradorId = moradorId;
        this.nomeMorador = nomeMorador;
        this.categoriaId = categoriaId;
        this.nomeCategoria = nomeCategoria;
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

    public Long getMoradorId() {
        return moradorId;
    }

    public String getNomeMorador() {
        return nomeMorador;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
}
