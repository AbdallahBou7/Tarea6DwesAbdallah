package com.viveroabdallahdwes4.Services;

import com.viveroabdallahdwes4.Modelo.Planta;
import com.viveroabdallahdwes4.Repository.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantaServicios {

    @Autowired
    private PlantaRepository plantaRepository;

    public List<Planta> findAll() {
        return plantaRepository.findAll();
    }

    public Planta save(Planta planta) {
        return plantaRepository.save(planta);
    }

    public Optional<Planta> findById(Long id) {
        return plantaRepository.findById(id);
    }

    public Planta update(Planta planta) {
        return plantaRepository.save(planta);
    }
}
