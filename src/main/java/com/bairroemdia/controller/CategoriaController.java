
package com.bairroemdia.controller;

import com.bairroemdia.bo.CategoriaBO;
import com.bairroemdia.model.Categoria;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaBO categoriaBO;

    public CategoriaController(CategoriaBO categoriaBO) {
        this.categoriaBO = categoriaBO;
    }

    // Cadastrar categoria
    @PostMapping
    public ResponseEntity<Categoria> cadastrar(
            @RequestBody Categoria categoria) {

        Categoria novaCategoria = categoriaBO.cadastrar(
                categoria.getNome(),
                categoria.getDescricao()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(novaCategoria);
    }

    // Listar todas as categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas() {
        return ResponseEntity.ok(categoriaBO.listarTodas());
    }

    // Buscar categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(categoriaBO.buscarPorId(id));
    }

    // Atualizar categoria
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(
            @PathVariable Long id,
            @RequestBody Categoria categoria) {

        Categoria atualizada = categoriaBO.atualizar(
                id,
                categoria.getNome(),
                categoria.getDescricao()
        );

        return ResponseEntity.ok(atualizada);
    }

    // Excluir categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        categoriaBO.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
