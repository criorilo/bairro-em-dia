
package com.bairroemdia.dao;

import com.bairroemdia.model.Ocorrencia;
import com.bairroemdia.model.StatusOcorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OcorrenciaDAO extends JpaRepository<Ocorrencia, Long> {

    List<Ocorrencia> findByStatus(StatusOcorrencia status);

    List<Ocorrencia> findByMoradorId(Long moradorId);

    List<Ocorrencia> findByCategoriaId(Long categoriaId);
}
