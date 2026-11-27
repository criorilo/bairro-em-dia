
package com.bairroemdia.controller;

import com.bairroemdia.bo.OcorrenciaBO;
import com.bairroemdia.model.Ocorrencia;
import com.bairroemdia.model.StatusOcorrencia;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    private final OcorrenciaBO ocorrenciaBO;

    public OcorrenciaController(OcorrenciaBO ocorrenciaBO) {
        this.ocorrenciaBO = ocorrenciaBO;
    }

    // Cadastrar ocorrência
    @PostMapping
    public ResponseEntity<Ocorrencia> cadastrar(
            @RequestParam String titulo,
            @RequestParam String descricao,
            @RequestParam String endereco,
            @RequestParam Long moradorId,
            @RequestParam Long categoriaId) {

        Ocorrencia nova = ocorrenciaBO.cadastrar(
                titulo, descricao, endereco, moradorId, categoriaId
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    // Listar todas as ocorrências
    @GetMapping
    public ResponseEntity<List<Ocorrencia>> listarTodas() {
        return ResponseEntity.ok(ocorrenciaBO.listarTodas());
    }

    // Buscar ocorrência por ID
    @GetMapping("/{id}")
    public ResponseEntity<Ocorrencia> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(ocorrenciaBO.buscarPorId(id));
    }

    // Atualizar ocorrência
    @PutMapping("/{id}")
    public ResponseEntity<Ocorrencia> atualizar(
            @PathVariable Long id,
            @RequestParam String titulo,
            @RequestParam String descricao,
            @RequestParam String endereco) {

        Ocorrencia atualizada = ocorrenciaBO.atualizar(
                id, titulo, descricao, endereco
        );

        return ResponseEntity.ok(atualizada);
    }

    // Atualizar status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Ocorrencia> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusOcorrencia status) {

        return ResponseEntity.ok(
                ocorrenciaBO.atualizarStatus(id, status)
        );
    }

    // Excluir ocorrência
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        ocorrenciaBO.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
