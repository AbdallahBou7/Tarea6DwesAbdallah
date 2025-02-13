package com.viveroabdallahdwes4.Controller;


import com.viveroabdallahdwes4.Modelo.*;
import com.viveroabdallahdwes4.Services.PlantaServicios;
import com.viveroabdallahdwes4.Services.EjemplarServicios;
import com.viveroabdallahdwes4.Services.MensajeServicios;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class ViveroController {

    @Autowired
    private PlantaServicios plantaService;

    @Autowired
    private EjemplarServicios ejemplarService;

    @Autowired
    private MensajeServicios mensajeService;

    @GetMapping("/")
    public String home() {
       return "redirect:/login";
    }

    @GetMapping("/menu")
    public String menu(HttpSession session, Model model) {
        Persona usuario = (Persona) session.getAttribute("usuario");
        
        if(usuario == null) {
            return "redirect:/login";
        }
        
        model.addAttribute("usuario", usuario);
        
        if (usuario.getRol() != null && usuario.getRol().equals(Rol.ADMIN)) {
            return "admin-menu";
        } else {
            return "personal-menu";
        }
    }

    // CU1: Ver Plantas 
    @GetMapping("/plantas")
    public String verPlantas(Model model) {
        model.addAttribute("plantas", plantaService.findAll());
        return "plantas";
    }
    
    @GetMapping("/plantasinvitado")
    public String mostrarPlantasInvitado(Model model) {
        model.addAttribute("plantas", plantaService.findAll());

        return "plantasinvitado";
    }

    // CU4A: Nueva Planta 
    @GetMapping("/plantas/nueva")
    public String nuevaPlantaForm(Model model, HttpSession session) {
        Persona usuario = (Persona) session.getAttribute("usuario");
        if(usuario == null || !usuario.getRol().equals(Rol.ADMIN)) {
        
            return "redirect:/login";
        }
        model.addAttribute("planta", new Planta());
        return "nuevaPlanta";
    }

    @PostMapping("/plantas/nueva")
    public String nuevaPlantaSubmit(@ModelAttribute Planta planta, HttpSession session) {
        Persona usuario = (Persona) session.getAttribute("usuario");
        if  (usuario == null || !usuario.getRol().equals(Rol.ADMIN)) {
            return "redirect:/login";
        }
        plantaService.save(planta);
        return "redirect:/plantas";
    }

    // CU4B: Modificar Planta 
    @GetMapping("/plantas/editar/{id}")
    public String editarPlantaForm(@PathVariable Long id, Model model, HttpSession session) {
        Persona usuario = (Persona) session.getAttribute("usuario");
        if (usuario == null || !usuario.getRol().equals(Rol.ADMIN)) {
            return "redirect:/login";
        }
        Optional<Planta> plantaOpt = plantaService.findById(id);
        if (plantaOpt.isPresent()) {
            model.addAttribute("planta", plantaOpt.get());
            return "editarPlanta";
        }
        return "redirect:/plantas";
    }

    @PostMapping("/plantas/editar")
    public String editarPlantaSubmit(@ModelAttribute Planta planta, HttpSession session) {
        Persona usuario = (Persona) session.getAttribute("usuario");
        if  (usuario == null || !usuario.getRol().equals(Rol.ADMIN)) {
            return "redirect:/login";
        }
        plantaService.update(planta);
        return "redirect:/plantas";
    }
    
    
    
    @GetMapping("/gestEjemplar")
    public String mostrarMenuGestionEjemplares(Model model, HttpSession session) {
        Object usuario = session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login"; // Si no hay sesión, redirigir al login
        }

        model.addAttribute("usuario", usuario);
        return "gestEjemplar"; // Nombre de la vista HTML
    }


    // CU5A: Registrar Nuevo Ejemplar 
    @GetMapping("/ejemplares")
    public String nuevoEjemplarForm(Model model, HttpSession session) {
        Persona usuario = (Persona) session.getAttribute("usuario");
        if(usuario == null || !usuario.getRol().equals(Rol.ADMIN)) {
            return "redirect:/login";
        }
        model.addAttribute("ejemplar", new Ejemplar());
        model.addAttribute("plantas", plantaService.findAll());
        return "nuevoEjemplar";
    }

    @PostMapping("/ejemplares/nuevo")
    public String nuevoEjemplarSubmit(@ModelAttribute Ejemplar ejemplar, HttpSession session) {
        Persona usuario = (Persona) session.getAttribute("usuario");
        if (usuario == null || !usuario.getRol().equals(Rol.ADMIN)) {
            return "redirect:/login";
        }
        ejemplar.setFechaRegistro(LocalDate.now());
        ejemplarService.save(ejemplar);
        return "redirect:/menu";
    }

    // CU5B: Filtrar Ejemplares por Tipo de Planta 
    @GetMapping("/ejemplares/filtrar")
    public String filtrarEjemplaresForm(Model model) {
        model.addAttribute("tipos", Arrays.asList("Interior", "Exterior", "Jardín"));
        return "filtrarEjemplares";
    }

    @PostMapping("/ejemplares/filtrar")
    public String filtrarEjemplaresSubmit(@RequestParam String tipo, Model model) {
        System.out.println("Tipo seleccionado: " + tipo);
        List<Ejemplar> ejemplares = ejemplarService.findByTipoPlanta(tipo);
        System.out.println("Ejemplares encontrados: " + ejemplares.size());
        model.addAttribute("ejemplares", ejemplares);
        return "listarEjemplares";
    }

    // CU5C: Ver Mensajes de Seguimiento de un Ejemplar 
    @GetMapping("/ejemplares/{id}/mensajes")
    public String verMensajesEjemplar(@PathVariable Long id, Model model) {
        List<Mensaje> mensajes = mensajeService.findByEjemplarId(id);
        model.addAttribute("mensajes", mensajes);
        return "mensajesEjemplar";
    }
    
    @GetMapping("/gestMensaje")
    public String mostrarMenuGestionMensajes(Model model, HttpSession session) {
        Object usuario = session.getAttribute("usuario");

        if (usuario == null) {
            return "redirect:/login"; 
        }

        model.addAttribute("usuario", usuario);
        return "gestMensaje"; 
    }

    // CU6: Gestión de Mensajes 
    
    @GetMapping("/listaMensajes")
    public String listarMensajes(Model model) {
        model.addAttribute("mensajes", mensajeService.obtenerTodos());
        return "listaMensajes"; 
    }
    
    @GetMapping("/mensajes/nuevo")
    public String nuevoMensajeForm(Model model, HttpSession session) {
        Persona usuario = (Persona) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        model.addAttribute("mensaje", new Mensaje());
        model.addAttribute("ejemplares", ejemplarService.findAll());
        return "nuevoMensaje";
    }

    @PostMapping("/mensajes/nuevo")
    public String nuevoMensajeSubmit(@ModelAttribute Mensaje mensaje, HttpSession session) {
        Persona usuario = (Persona) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }
        mensaje.setFechaMensaje(LocalDateTime.now());
        mensajeService.save(mensaje);
        return "redirect:/menu";
    }
    
    @GetMapping("/mensajes/listaMensajes")
    public List<Mensaje> listarMensajes() {
        return mensajeService.obtenerTodos();
    }

    
    @GetMapping("/filtrar/persona")
    public List<Mensaje> filtrarPorPersona(@RequestParam Long personaId) {
        Persona persona = new Persona();
        persona.setId(personaId);
        return mensajeService.filtrarPorPersona(persona);
    }

    @GetMapping("/filtrar/fecha")
    public List<Mensaje> filtrarPorFecha(@RequestParam String inicio, @RequestParam String fin) {
        LocalDateTime fechaInicio = LocalDateTime.parse(inicio);
        LocalDateTime fechaFin = LocalDateTime.parse(fin);
        return mensajeService.filtrarPorFecha(fechaInicio, fechaFin);
    }

    @GetMapping("/filtrar/tipoPlanta")
    public List<Mensaje> filtrarPorTipoPlanta(@RequestParam String tipo) {
        return mensajeService.filtrarPorTipoPlanta(tipo);
    }
}
