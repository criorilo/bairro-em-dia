
package com.bairroemdia.controller;

import com.bairroemdia.bo.MoradorBO;
import com.bairroemdia.dto.MoradorDTO;
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
    public ResponseEntity<MoradorDTO> cadastrar(
            @RequestBody MoradorDTO dto) {

        Morador morador = moradorBO.cadastrar(
                dto.getNome(),
                dto.getEmail(),
                dto.getTelefone()
        );

        MoradorDTO resposta = new MoradorDTO(
                morador.getId(),
                morador.getNome(),
                morador.getEmail(),
                morador.getTelefone()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(resposta);
    }

    // Listar todos os moradores
    @GetMapping
    public ResponseEntity<List<MoradorDTO>> listarTodos() {

        List<MoradorDTO> moradores = moradorBO.listarTodos()
                .stream()
                .map(m -> new MoradorDTO(
                        m.getId(),
                        m.getNome(),
                        m.getEmail(),
                        m.getTelefone()
                ))
                .toList();

        return ResponseEntity.ok(moradores);
    }

    // Buscar morador por ID
    @GetMapping("/{id}")
    public ResponseEntity<MoradorDTO> buscarPorId(
            @PathVariable Long id) {

        Morador m = moradorBO.buscarPorId(id);

        MoradorDTO dto = new MoradorDTO(
                m.getId(),
                m.getNome(),
                m.getEmail(),
                m.getTelefone()
        );

        return ResponseEntity.ok(dto);
    }

    // Atualizar morador
    @PutMapping("/{id}")
    public ResponseEntity<MoradorDTO> atualizar(
            @PathVariable Long id,
            @RequestBody MoradorDTO dto) {

        Morador m = moradorBO.atualizar(
                id,
                dto.getNome(),
                dto.getEmail(),
                dto.getTelefone()
        );

        MoradorDTO resposta = new MoradorDTO(
                m.getId(),
                m.getNome(),
                m.getEmail(),
                m.getTelefone()
        );

        return ResponseEntity.ok(resposta);
    }

    // Excluir morador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        moradorBO.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
