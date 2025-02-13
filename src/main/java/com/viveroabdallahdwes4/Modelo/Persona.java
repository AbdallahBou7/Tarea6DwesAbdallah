package com.viveroabdallahdwes4.Modelo;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="personas")
public class Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String nombre;
	
	@Column(unique=true)
	private String email;
	
	@Enumerated(EnumType.STRING)
    private Rol rol;
	
	 
	 @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
	    private Credencial credencial;
	 
	 @OneToMany(mappedBy ="persona", cascade = CascadeType.ALL)
	 private List<Mensaje> mensajes;


	public Credencial getCredencial() {
		return credencial;
	}


	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	


	public List<Mensaje> getMensajes() {
		return mensajes;
	}


	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	
	

	public Rol getRol() {
		return rol;
	}


	public void setRol(Rol rol) {
		this.rol = rol;
	}


	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", email=" + email + ", mensajes=" + mensajes + "]";
	}


	


	

	


	 
	 
	 
	
	
	

}
