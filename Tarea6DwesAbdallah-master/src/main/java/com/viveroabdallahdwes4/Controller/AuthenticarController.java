package com.viveroabdallahdwes4.Controller;

import com.viveroabdallahdwes4.Modelo.Credencial;
import com.viveroabdallahdwes4.Modelo.Persona;
import com.viveroabdallahdwes4.Modelo.Rol;
import com.viveroabdallahdwes4.Repository.CredencialRepository;
import com.viveroabdallahdwes4.Repository.PersonaRepository;
import com.viveroabdallahdwes4.Services.CredencialServicios;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/")

public class AuthenticarController {
	

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private CredencialRepository credencialRepository;
    
    @Autowired
    private  CredencialServicios credencialService;
  

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("credencial", new Credencial());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("credencial") Credencial credencial,
                              HttpSession session, Model model) {
       if (credencialService.authenticate(credencial.getUsuario(), credencial.getPassword())) {
            Credencial credencialLogueada = credencialService.findByUsuario(credencial.getUsuario()).get();
            session.setAttribute("usuario", credencialLogueada.getPersona());
            return "redirect:/menu";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
    @GetMapping("/registrar")

    public String mostrarFormularioRegistro(Model model) {
    	Persona persona = new Persona();
        persona.setCredencial(new Credencial()); 

        model.addAttribute("persona", persona);
        return "registrar";  
    }

    @PostMapping("/registrar")
    @Transactional
    public String registrarPersona(@ModelAttribute Persona persona, Model model) {
    	if (persona == null || persona.getCredencial() == null) {
            model.addAttribute("error", "Error: La persona o credencial no pueden ser nulas.");
            return "registrar";
        }

    	
        persona.setNombre(persona.getNombre().trim());
        persona.getCredencial().setUsuario(persona.getCredencial().getUsuario().trim());
        persona.getCredencial().setPassword(persona.getCredencial().getPassword().trim());

        try {
            persona.setRol(Rol.valueOf(persona.getRol().name()));
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Rol inválido. Selecciona un rol correcto.");
            return "registrar";
        }
      
        Optional<Persona> personaExistente = personaRepository.findByEmail(persona.getEmail());
        if (personaExistente.isPresent()) {
            model.addAttribute("error", "El email ya está registrado. Usa otro.");
        }

       
        Optional<Credencial> credencialExistente = credencialRepository.findByUsuario(persona.getCredencial().getUsuario());
        if (credencialExistente.isPresent()) {
            model.addAttribute("error", "El nombre de usuario ya está en uso. Usa otro.");
        }

       
        if (persona.getNombre().isEmpty()) {
            model.addAttribute("error", "El nombre no puede estar vacío o contener solo espacios.");
        }

       
        if (persona.getCredencial().getPassword().contains(" ")) {
            model.addAttribute("error", "La contraseña no puede contener espacios.");
        }

       
        persona = personaRepository.save(persona);
        persona.getCredencial().setPersona(persona);
        credencialRepository.save(persona.getCredencial());

        return "redirect:/lista-personas";
    }
    
    @GetMapping("/lista-personas")
    public String listarPersonas(Model model) {
        List<Persona> personas = personaRepository.findAll();
        model.addAttribute("personas", personas);
        return "lista-personas";  
    }
}