package com.viveroabdallahdwes4.Repository;

import com.viveroabdallahdwes4.Modelo.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {
	List<Ejemplar> findByPlanta_Nombrecomun(String nombrecomun);
	List<Ejemplar> findByPlantaTipo(String tipo);

}
