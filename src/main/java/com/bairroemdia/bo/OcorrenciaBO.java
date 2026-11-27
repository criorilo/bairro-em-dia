
package com.bairroemdia.bo;

import com.bairroemdia.dao.OcorrenciaDAO;
import com.bairroemdia.dao.MoradorDAO;
import com.bairroemdia.dao.CategoriaDAO;
import com.bairroemdia.model.Ocorrencia;
import com.bairroemdia.model.Morador;
import com.bairroemdia.model.Categoria;
import com.bairroemdia.model.StatusOcorrencia;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcorrenciaBO {

    private final OcorrenciaDAO ocorrenciaDAO;
    private final MoradorDAO moradorDAO;
    private final CategoriaDAO categoriaDAO;

    public OcorrenciaBO(
            OcorrenciaDAO ocorrenciaDAO,
            MoradorDAO moradorDAO,
            CategoriaDAO categoriaDAO) {

        this.ocorrenciaDAO = ocorrenciaDAO;
        this.moradorDAO = moradorDAO;
        this.categoriaDAO = categoriaDAO;
    }

    // Cadastrar ocorrência
    public Ocorrencia cadastrar(
            String titulo,
            String descricao,
            String endereco,
            Long moradorId,
            Long categoriaId) {

        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException(
                    "O título da ocorrência é obrigatório.");
        }

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException(
                    "A descrição da ocorrência é obrigatória.");
        }

        if (endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException(
                    "O endereço é obrigatório.");
        }

        Morador morador = moradorDAO.findById(moradorId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Morador não encontrado."));

        Categoria categoria = categoriaDAO.findById(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Categoria não encontrada."));

        Ocorrencia ocorrencia = new Ocorrencia(
                titulo, descricao, endereco, morador, categoria);

        return ocorrenciaDAO.save(ocorrencia);
    }

    // Listar todas as ocorrências
    public List<Ocorrencia> listarTodas() {
        return ocorrenciaDAO.findAll();
    }

    // Buscar ocorrência por ID
    public Ocorrencia buscarPorId(Long id) {
        return ocorrenciaDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Ocorrência não encontrada."));
    }

    // Atualizar ocorrência
    public Ocorrencia atualizar(
            Long id,
            String titulo,
            String descricao,
            String endereco) {

        Ocorrencia ocorrencia = buscarPorId(id);

        ocorrencia.atualizar(titulo, descricao, endereco);

        return ocorrenciaDAO.save(ocorrencia);
    }

    // Atualizar status
    public Ocorrencia atualizarStatus(
            Long id,
            StatusOcorrencia novoStatus) {

        Ocorrencia ocorrencia = buscarPorId(id);

        if (novoStatus == null) {
            throw new IllegalArgumentException(
                    "O novo status é obrigatório.");
        }

        if (ocorrencia.getStatus() == StatusOcorrencia.RESOLVIDA
                || ocorrencia.getStatus() == StatusOcorrencia.CANCELADA) {
            throw new IllegalStateException(
                    "Não é possível alterar uma ocorrência finalizada.");
        }

        ocorrencia.atualizarStatus(novoStatus);

        return ocorrenciaDAO.save(ocorrencia);
    }

    // Excluir ocorrência
    public void excluir(Long id) {
        Ocorrencia ocorrencia = buscarPorId(id);
        ocorrenciaDAO.delete(ocorrencia);
    }
}
