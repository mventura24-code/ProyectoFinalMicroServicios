package com.mv.microservices.factura.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClienteDto {
    
    @JsonProperty("id")
    private Integer id;           
   
    @JsonProperty("nombre")
    private String nombre;         
    
    @JsonProperty("email")
    private String email;          
    
    @JsonProperty("telefono")
    private String telefono;       
    
    @JsonProperty("direccion")
    private String direccion;      
    
    @JsonProperty("nit")
    private String nit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}
    
    
}
