package com.example.clientesalmuerzo.controllers;

import com.example.clientesalmuerzo.domain.Cliente;
import com.example.clientesalmuerzo.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes") // Cambiado para no generar conflicto con Thymeleaf
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Obtener todos los clientes
    @GetMapping
    public List<Cliente> getClientes() {
        return clienteService.getClientes();
    }

    // Obtener un cliente por nombre
    @GetMapping("/{nombre}")
    public ResponseEntity<Cliente> getCliente(@PathVariable String nombre) {
        return clienteService.getCliente(nombre);
    }

    // Agregar un cliente
    @PostMapping
    public ResponseEntity<?> addCliente(@RequestBody Cliente cliente) {
        return clienteService.addCliente(cliente);
    }

    // Actualizar el campo "pago" (cambia si ↔ no)
    @PutMapping("/{nombre}")
    public Cliente updatePago(@PathVariable String nombre) {
        return clienteService.updatePago(nombre);
    }

    // Eliminar un cliente
    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> deleteCliente(@PathVariable String nombre) {
        return clienteService.deleteCliente(nombre);
    }
}
