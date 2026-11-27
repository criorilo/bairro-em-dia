
package com.bairroemdia.dao;

import com.bairroemdia.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaDAO extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nome);
}
