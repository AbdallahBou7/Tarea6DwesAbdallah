package com.viveroabdallahdwes4.Repository;

import com.viveroabdallahdwes4.Modelo.Mensaje;
import com.viveroabdallahdwes4.Modelo.Persona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
	List<Mensaje> findByPersona(Persona persona);
    List<Mensaje> findByFechaMensajeBetween(LocalDateTime start, LocalDateTime end);
    List<Mensaje> findByEjemplarPlantaTipo(String tipo);
	List<Mensaje> findByEjemplar_Id(Long ejemplarId);
}