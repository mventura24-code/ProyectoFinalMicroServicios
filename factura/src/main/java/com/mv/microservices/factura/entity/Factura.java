package com.mv.microservices.factura.entity;

import java.time.LocalDate;
import java.util.List;

import com.mv.microservices.factura.dto.ProductoDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="facturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductoFactura> productosFactura; 
	
	@Column(name = "cliente_Id", nullable = false)
    private Integer clienteId;
	
    private String nit;
    private String nombreCliente;
    private String numeroFactura;
    private Double total;
    private LocalDate fecha;   
    
    public void calcularTotal() {
        this.total = productosFactura.stream()
                .mapToDouble(pf -> pf.getPrecio() * pf.getCantidad())
                .sum();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ProductoFactura> getProductosFactura() {
		return productosFactura;
	}

	public void setProductosFactura(List<ProductoFactura> productosFactura) {
		this.productosFactura = productosFactura;
	}

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
    	
}
