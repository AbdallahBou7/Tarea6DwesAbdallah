package com.viveroabdallahdwes4.Services;


import com.viveroabdallahdwes4.Modelo.Ejemplar;
import com.viveroabdallahdwes4.Repository.EjemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjemplarServicios {

    @Autowired
    private EjemplarRepository ejemplarRepository;

    public Ejemplar save(Ejemplar ejemplar) {
        return ejemplarRepository.save(ejemplar);
    }

    public List<Ejemplar> findByTipoPlanta(String nombrecomun) {
        return ejemplarRepository.findByPlanta_Nombrecomun(nombrecomun);
    }
    
    public List<Ejemplar> findAll() {
        return ejemplarRepository.findAll();
    }

	
}
