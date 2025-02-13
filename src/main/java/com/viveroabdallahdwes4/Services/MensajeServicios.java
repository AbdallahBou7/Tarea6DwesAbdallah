package com.viveroabdallahdwes4.Services;


import com.viveroabdallahdwes4.Modelo.Mensaje;
import com.viveroabdallahdwes4.Modelo.Persona;
import com.viveroabdallahdwes4.Repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MensajeServicios {

    @Autowired
    private MensajeRepository mensajeRepository;

    public Mensaje save(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }

    public List<Mensaje> findByEjemplarId(Long ejemplarId) {
        return mensajeRepository.findByEjemplar_Id(ejemplarId);
    }

	public List<Mensaje> obtenerTodos() {
		return mensajeRepository.findAll();
	}

	public List<Mensaje> filtrarPorPersona(Persona persona) {
		return mensajeRepository.findByPersona(persona);
	}
	
	public List<Mensaje> filtrarPorFecha(LocalDateTime inicio, LocalDateTime fin) {
        return mensajeRepository.findByFechaMensajeBetween(inicio, fin);
    }
    
    public List<Mensaje> filtrarPorTipoPlanta(String tipo) {
        return mensajeRepository.findByEjemplarPlantaTipo(tipo);
    }

	
}

