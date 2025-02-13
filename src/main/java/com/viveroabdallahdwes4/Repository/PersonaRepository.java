package com.viveroabdallahdwes4.Repository;

import com.viveroabdallahdwes4.Modelo.Persona;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

	Optional<Persona> findByEmail(String email);

}