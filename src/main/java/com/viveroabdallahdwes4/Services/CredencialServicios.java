package com.viveroabdallahdwes4.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viveroabdallahdwes4.Modelo.Credencial;
import com.viveroabdallahdwes4.Repository.CredencialRepository;

@Service
public class CredencialServicios {

    @Autowired
    private CredencialRepository credencialRepository;

    public Optional<Credencial> findByUsuario(String usuario) {
        return credencialRepository.findByUsuario(usuario);
    }

    public boolean authenticate(String usuario, String password) {
        Optional<Credencial> credencialOpt = credencialRepository.findByUsuario(usuario);
        if (credencialOpt.isPresent()) {
            Credencial credencial = credencialOpt.get();
            return credencial.getPassword().equals(password);
        }
        return false;
    }
    
 
    public Credencial save(Credencial credencial) {
        return credencialRepository.save(credencial);
    }

	
}
