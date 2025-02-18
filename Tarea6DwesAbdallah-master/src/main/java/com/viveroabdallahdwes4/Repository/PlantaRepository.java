package com.viveroabdallahdwes4.Repository;

import com.viveroabdallahdwes4.Modelo.Planta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantaRepository extends JpaRepository<Planta, Long> {
    Optional<Planta> findByNombrecomun(String nombrecomun);

}
