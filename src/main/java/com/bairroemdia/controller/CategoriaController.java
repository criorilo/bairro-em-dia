
package com.bairroemdia.controller;

import com.bairroemdia.bo.CategoriaBO;
import com.bairroemdia.dto.CategoriaDTO;
import com.bairroemdia.model.Categoria;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaBO categoriaBO;

    public CategoriaController(CategoriaBO categoriaBO) {
        this.categoriaBO = categoriaBO;
    }

    // Cadastrar categoria
    @PostMapping
    public ResponseEntity<CategoriaDTO> cadastrar(
            @Valid @RequestBody CategoriaDTO dto) {

        Categoria categoria = categoriaBO.cadastrar(
                dto.getNome(),
                dto.getDescricao()
        );

        CategoriaDTO resposta = new CategoriaDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resposta);
    }

    // Listar todas as categorias
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarTodas() {

        List<CategoriaDTO> categorias = categoriaBO.listarTodas()
                .stream()
                .map(c -> new CategoriaDTO(
                        c.getId(),
                        c.getNome(),
                        c.getDescricao()
                ))
                .toList();

        return ResponseEntity.ok(categorias);
    }

    // Buscar categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarPorId(
            @PathVariable Long id) {

        Categoria c = categoriaBO.buscarPorId(id);

        CategoriaDTO dto = new CategoriaDTO(
                c.getId(),
                c.getNome(),
                c.getDescricao()
        );

        return ResponseEntity.ok(dto);
    }

    // Atualizar categoria
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizar(
            @PathVariable Long id,
            @RequestBody CategoriaDTO dto) {

        Categoria c = categoriaBO.atualizar(
                id,
                dto.getNome(),
                dto.getDescricao()
        );

        CategoriaDTO resposta = new CategoriaDTO(
                c.getId(),
                c.getNome(),
                c.getDescricao()
        );

        return ResponseEntity.ok(resposta);
    }

    // Excluir categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        categoriaBO.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
