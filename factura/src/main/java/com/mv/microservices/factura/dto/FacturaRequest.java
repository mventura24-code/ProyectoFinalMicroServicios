package com.mv.microservices.factura.dto;

import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class FacturaRequest {

	private Integer clienteId;
	private String nit;
	private String nombreCliente;
	private String numeroFactura;
    private Double total;
    
    @Column(updatable = false)
    private LocalDate fecha;
	private List<ProductoRequest> productosFactura; 
	
	public Integer getClienteId() {
		return clienteId;
	}
	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public List<ProductoRequest> getProductosFactura() {
		return productosFactura;
	}
	public void setProductosFactura(List<ProductoRequest> productosFactura) {
		this.productosFactura = productosFactura;
	}
	public FacturaRequest(Integer clienteId, String nit, String nombreCliente, String numeroFactura, Double total,
			LocalDate fecha, List<ProductoRequest> productosFactura) {
		super();
		this.clienteId = clienteId;
		this.nit = nit;
		this.nombreCliente = nombreCliente;
		this.numeroFactura = numeroFactura;
		this.total = total;
		this.fecha = fecha;
		this.productosFactura = productosFactura;
	}
	
	public FacturaRequest() {
		super();
	}

    
}
