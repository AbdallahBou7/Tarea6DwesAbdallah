package com.viveroabdallahdwes4.Modelo;

import java.time.LocalDate; 
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ejemplares")
public class Ejemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  

    @Column
    private String nombre;
    
    
     @Column(name = "fecha_registro")
     private LocalDate fechaRegistro;
    
    @ManyToOne
    @JoinColumn(name = "planta_id") 
    private Planta planta;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ejemplar_id") // 
    private List<Mensaje> mensajes = new LinkedList<>();

    public Ejemplar() {
        super();
    }

    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public Planta getPlanta() {
        return planta;
    }
    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    
    public List<Mensaje> getMensajes() {
        return mensajes;
    }
    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
    
    
    public LocalDate getFechaRegistro() {
       return fechaRegistro;
     }
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
     }

    @Override
    public String toString() {
        return "Ejemplar [id=" + id + ", nombre=" + nombre + ", planta=" + planta + ", mensajes=" + mensajes + "]";
    }


	
}
