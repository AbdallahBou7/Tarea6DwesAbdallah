package com.viveroabdallahdwes4.Repository;

import com.viveroabdallahdwes4.Modelo.Credencial;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long> {

	Optional<Credencial> findByUsuario(String usuario);
    
}
