
package com.bairroemdia.dao;

import com.bairroemdia.model.Morador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoradorDAO extends JpaRepository<Morador, Long> {

    Optional<Morador> findByEmail(String email);
}
