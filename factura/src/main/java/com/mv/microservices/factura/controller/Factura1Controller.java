package com.mv.microservices.factura.controller;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;

import com.mv.microservices.factura.dto.ClienteDto;
import com.mv.microservices.factura.dto.FacturaRequest;
import com.mv.microservices.factura.dto.ProductoDto;
import com.mv.microservices.factura.dto.ProductoRequest;
import com.mv.microservices.factura.entity.Factura;
import com.mv.microservices.factura.entity.ProductoFactura;
import com.mv.microservices.factura.services.IFactura;

@RestController
@RequestMapping("/api/factura1")
public class Factura1Controller {
	
	@Autowired
	private IFactura facturaService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<ClienteDto> getClienteById(@PathVariable Integer id) {
	    String url = "http://localhost:8081/api/clientes/" + id; 
	    try {
	        ResponseEntity<ClienteDto> response = restTemplate.getForEntity(url, ClienteDto.class);
	        if (response.getStatusCode() == HttpStatus.OK) {
	            return ResponseEntity.ok(response.getBody());
	        } else {
	            return ResponseEntity.status(response.getStatusCode())
	                                 .body(null);
	        }
	    } catch (HttpClientErrorException e) {
	    	System.out.println("Error al obtener cliente: " + e.getResponseBodyAsString());
	        return ResponseEntity.status(e.getStatusCode())
	                             .body(null);
	    }
	}
	
	@GetMapping("/productos/{idProducto}")
    public ResponseEntity<ProductoDto> getProductoById_Producto(@PathVariable Integer idProducto) {
        String url = "http://localhost:8082/api/productos/" + idProducto; 
        try {
            ResponseEntity<ProductoDto> response = restTemplate.getForEntity(url, ProductoDto.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(response.getStatusCode()).body(null);
            }
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }
	
	
	@PostMapping("/guardar")
	public ResponseEntity<Factura> guardarFactura(@RequestBody FacturaRequest facturaRequest) {
	    Factura factura = facturaService.crearFactura(facturaRequest);
	    return ResponseEntity.status(HttpStatus.CREATED).body(factura);
	}
	
    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody FacturaRequest facturaRequest) {
        try {
            // Convertir FacturaRequest a Factura antes de guardar
            Factura nuevaFactura = new Factura();
            nuevaFactura.setClienteId(facturaRequest.getClienteId());
            nuevaFactura.setNit(facturaRequest.getNit());
            nuevaFactura.setNombreCliente(facturaRequest.getNombreCliente());
            nuevaFactura.setNumeroFactura(facturaRequest.getNumeroFactura());
            nuevaFactura.setTotal(facturaRequest.getTotal());
            nuevaFactura.setFecha(LocalDate.now());

            List<ProductoFactura> productos = facturaRequest.getProductosFactura().stream()
            	    .map(prodRequest -> {
            	        ProductoFactura productoFactura = new ProductoFactura();
            	        productoFactura.setNombreProducto(prodRequest.getNombreProducto());
            	        productoFactura.setPrecio(prodRequest.getPrecio());
            	        productoFactura.setCantidad(prodRequest.getCantidad());
            	        productoFactura.setProductoId(prodRequest.getProductoId());
            	        productoFactura.setFactura(nuevaFactura); // Establecer la relación con la factura
            	        return productoFactura;
            	    })
            	    .collect(Collectors.toList());

            	nuevaFactura.setProductosFactura(productos);
            	
            // Guardar la factura
            Factura savedFactura = facturaService.save(nuevaFactura);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFactura);
        } catch (Exception e) {
            System.out.println("Error al crear la factura: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
