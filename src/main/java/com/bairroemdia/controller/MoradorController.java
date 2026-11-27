
package com.bairroemdia.controller;

import com.bairroemdia.bo.MoradorBO;
import com.bairroemdia.model.Morador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moradores")
public class MoradorController {

    private final MoradorBO moradorBO;

    public MoradorController(MoradorBO moradorBO) {
        this.moradorBO = moradorBO;
    }

    // Cadastrar morador
    @PostMapping
    public ResponseEntity<Morador> cadastrar(
            @RequestBody Morador morador) {

        Morador novoMorador = moradorBO.cadastrar(
                morador.getNome(),
                morador.getEmail(),
                morador.getTelefone()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(novoMorador);
    }

    // Listar todos os moradores
    @GetMapping
    public ResponseEntity<List<Morador>> listarTodos() {
        return ResponseEntity.ok(moradorBO.listarTodos());
    }

    // Buscar morador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Morador> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(moradorBO.buscarPorId(id));
    }

    // Atualizar morador
    @PutMapping("/{id}")
    public ResponseEntity<Morador> atualizar(
            @PathVariable Long id,
            @RequestBody Morador morador) {

        Morador atualizado = moradorBO.atualizar(
                id,
                morador.getNome(),
                morador.getEmail(),
                morador.getTelefone()
        );

        return ResponseEntity.ok(atualizado);
    }

    // Excluir morador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        moradorBO.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
