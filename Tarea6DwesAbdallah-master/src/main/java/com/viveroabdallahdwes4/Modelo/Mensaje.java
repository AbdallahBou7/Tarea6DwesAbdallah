package com.viveroabdallahdwes4.Modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "mensajes")

public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 500)
    private String contenido;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_persona")
    private Persona persona;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_ejemplar")
    private Ejemplar ejemplar;
    
    @Column(nullable = false)
    private LocalDateTime fechaMensaje;
    
    public Mensaje() {}
    
    public Mensaje(String contenido, Persona persona, Ejemplar ejemplar) {
        this.contenido = contenido;
        this.persona = persona;
        this.ejemplar = ejemplar;
        this.fechaMensaje = LocalDateTime.now();
    }
    
    public Long getId() { 
    	return id; }
    
    public void setId(Long id) { 
    	this.id = id; }
    
    public String getContenido() { 
    	return contenido; }
    
    
    public void setContenido(String contenido) {
    	this.contenido = contenido; }
    
    public Persona getPersona() { 
    	return persona; }
    
    
    public void setPersona(Persona persona) { 
    	this.persona = persona; }
    
    
    public Ejemplar getEjemplar() {
    	return ejemplar; }
    
    
    public void setEjemplar(Ejemplar ejemplar) { 
    	this.ejemplar = ejemplar; }
    
    
    public LocalDateTime getFechaMensaje() { 
    	return fechaMensaje; }
    
    
    public void setFechaMensaje(LocalDateTime fechaMensaje) { 
    	this.fechaMensaje = fechaMensaje; }

	
}
	
	
	
	

