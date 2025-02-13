package com.viveroabdallahdwes4.Services;

import com.viveroabdallahdwes4.Modelo.Persona;
import com.viveroabdallahdwes4.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PersonaServicios {

    @Autowired
    private PersonaRepository personaRepository;

   
    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    public Optional<Persona> findById(Long id) {
        return personaRepository.findById(id);
    }
    
    
}
