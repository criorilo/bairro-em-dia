
package com.bairroemdia.controller;

import com.bairroemdia.bo.OcorrenciaBO;
import com.bairroemdia.dto.OcorrenciaDTO;
import com.bairroemdia.model.Ocorrencia;
import com.bairroemdia.model.StatusOcorrencia;

import jakarta.validation.Valid;

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

    @PostMapping
    public ResponseEntity<OcorrenciaDTO> cadastrar(
            @Valid @RequestBody OcorrenciaDTO dto) {

        Ocorrencia o = ocorrenciaBO.cadastrar(
                dto.getTitulo(),
                dto.getDescricao(),
                dto.getEndereco(),
                dto.getMoradorId(),
                dto.getCategoriaId()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(converterParaDTO(o));
    }

    @GetMapping
    public ResponseEntity<List<OcorrenciaDTO>> listarTodas() {

        List<OcorrenciaDTO> ocorrencias = ocorrenciaBO.listarTodas()
                .stream()
                .map(this::converterParaDTO)
                .toList();

        return ResponseEntity.ok(ocorrencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                converterParaDTO(ocorrenciaBO.buscarPorId(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<OcorrenciaDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody OcorrenciaDTO dto) {

        Ocorrencia o = ocorrenciaBO.atualizar(
                id,
                dto.getTitulo(),
                dto.getDescricao(),
                dto.getEndereco()
        );

        return ResponseEntity.ok(converterParaDTO(o));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OcorrenciaDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusOcorrencia status) {

        Ocorrencia o = ocorrenciaBO.atualizarStatus(id, status);

        return ResponseEntity.ok(converterParaDTO(o));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        ocorrenciaBO.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
