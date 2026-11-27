
package com.bairroemdia.bo;

import com.bairroemdia.dao.MoradorDAO;
import com.bairroemdia.model.Morador;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoradorBO {

    private final MoradorDAO moradorDAO;

    public MoradorBO(MoradorDAO moradorDAO) {
        this.moradorDAO = moradorDAO;
    }

    // Cadastrar morador
    public Morador cadastrar(String nome, String email, String telefone) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do morador é obrigatório.");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "O e-mail do morador é obrigatório.");
        }

        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException(
                    "O telefone do morador é obrigatório.");
        }

        if (moradorDAO.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException(
                    "Já existe um morador cadastrado com este e-mail.");
        }

        Morador morador = new Morador(nome, email, telefone);

        return moradorDAO.save(morador);
    }

    // Listar todos os moradores
    public List<Morador> listarTodos() {
        return moradorDAO.findAll();
    }

    // Buscar morador por ID
    public Morador buscarPorId(Long id) {
        return moradorDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Morador não encontrado."));
    }

    // Atualizar dados do morador
    public Morador atualizar(
            Long id,
            String nome,
            String email,
            String telefone) {

        Morador morador = buscarPorId(id);

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome é obrigatório.");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException(
                    "O e-mail é obrigatório.");
        }

        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException(
                    "O telefone é obrigatório.");
        }

        moradorDAO.findByEmail(email).ifPresent(existente -> {
            if (!existente.getId().equals(id)) {
                throw new IllegalArgumentException(
                        "Este e-mail já está cadastrado para outro morador.");
            }
        });

        morador.atualizar(nome, email, telefone);

        return moradorDAO.save(morador);
    }

    // Excluir morador
    public void excluir(Long id) {
        Morador morador = buscarPorId(id);
        moradorDAO.delete(morador);
    }
}
