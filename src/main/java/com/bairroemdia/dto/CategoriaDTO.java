
package com.bairroemdia.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaDTO {

    private Long id;

    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;

    @NotBlank(message = "A descrição da categoria é obrigatória")
    private String descricao;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
