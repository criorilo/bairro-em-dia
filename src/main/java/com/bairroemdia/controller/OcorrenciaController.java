
package com.bairroemdia.controller;

import com.bairroemdia.bo.OcorrenciaBO;
import com.bairroemdia.dto.OcorrenciaDTO;
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

    // Converter entidade em DTO
    private OcorrenciaDTO converterParaDTO(Ocorrencia o) {
        return new OcorrenciaDTO(
                o.getId(),
                o.getTitulo(),
                o.getDescricao(),
                o.getEndereco(),
                o.getStatus(),
                o.getDataRegistro(),
                o.getMorador().getId(),
                o.getMorador().getNome(),
                o.getCategoria().getId(),
                o.getCategoria().getNome()
        );
    }

    // Cadastrar ocorrência
    @PostMapping
    public ResponseEntity<OcorrenciaDTO> cadastrar(
            @RequestParam String titulo,
            @RequestParam String descricao,
            @RequestParam String endereco,
            @RequestParam Long moradorId,
            @RequestParam Long categoriaId) {

        Ocorrencia o = ocorrenciaBO.cadastrar(
                titulo, descricao, endereco, moradorId, categoriaId
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(converterParaDTO(o));
    }

    // Listar todas as ocorrências
    @GetMapping
    public ResponseEntity<List<OcorrenciaDTO>> listarTodas() {

        List<OcorrenciaDTO> ocorrencias = ocorrenciaBO.listarTodas()
                .stream()
                .map(this::converterParaDTO)
                .toList();

        return ResponseEntity.ok(ocorrencias);
    }

    // Buscar ocorrência por ID
    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                converterParaDTO(ocorrenciaBO.buscarPorId(id))
        );
    }

    // Atualizar ocorrência
    @PutMapping("/{id}")
    public ResponseEntity<OcorrenciaDTO> atualizar(
            @PathVariable Long id,
            @RequestParam String titulo,
            @RequestParam String descricao,
            @RequestParam String endereco) {

        Ocorrencia o = ocorrenciaBO.atualizar(
                id, titulo, descricao, endereco
        );

        return ResponseEntity.ok(converterParaDTO(o));
    }

    // Atualizar status
    @PatchMapping("/{id}/status")
    public ResponseEntity<OcorrenciaDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusOcorrencia status) {

        Ocorrencia o = ocorrenciaBO.atualizarStatus(id, status);

        return ResponseEntity.ok(converterParaDTO(o));
    }

    // Excluir ocorrência
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        ocorrenciaBO.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
