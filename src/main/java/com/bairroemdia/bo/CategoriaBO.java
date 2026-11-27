
package com.bairroemdia.bo;

import com.bairroemdia.dao.CategoriaDAO;
import com.bairroemdia.model.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaBO {

    private final CategoriaDAO categoriaDAO;

    public CategoriaBO(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    // Cadastrar categoria
    public Categoria cadastrar(String nome, String descricao) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome da categoria é obrigatório.");
        }

        if (categoriaDAO.findByNome(nome).isPresent()) {
            throw new IllegalArgumentException(
                    "Esta categoria já está cadastrada.");
        }

        Categoria categoria = new Categoria(nome, descricao);

        return categoriaDAO.save(categoria);
    }

    // Listar todas as categorias
    public List<Categoria> listarTodas() {
        return categoriaDAO.findAll();
    }

    // Buscar categoria por ID
    public Categoria buscarPorId(Long id) {
        return categoriaDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Categoria não encontrada."));
    }

    // Atualizar categoria
    public Categoria atualizar(Long id, String nome, String descricao) {

        Categoria categoria = buscarPorId(id);

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome da categoria é obrigatório.");
        }

        categoriaDAO.findByNome(nome).ifPresent(existente -> {
            if (!existente.getId().equals(id)) {
                throw new IllegalArgumentException(
                        "Já existe outra categoria com este nome.");
            }
        });

        categoria.atualizar(nome, descricao);

        return categoriaDAO.save(categoria);
    }

    // Excluir categoria
    public void excluir(Long id) {
        Categoria categoria = buscarPorId(id);
        categoriaDAO.delete(categoria);
    }
}
