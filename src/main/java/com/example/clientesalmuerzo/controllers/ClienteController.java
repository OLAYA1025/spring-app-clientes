package com.example.clientesalmuerzo.controllers;

import com.example.clientesalmuerzo.domain.Cliente;
import com.example.clientesalmuerzo.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getClientes() {
        return clienteService.getClientes();
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<Cliente> getCliente(@PathVariable String nombre) {
        return clienteService.getCliente(nombre);
    }

    @PostMapping
    public ResponseEntity<?> addCliente(@RequestBody Cliente cliente) {
        return clienteService.addCliente(cliente);
    }

    @PutMapping("/{nombre}")
    public Cliente updatePago(@PathVariable String nombre) {
        return clienteService.updatePago(nombre);
    }

    @PutMapping("/entregado/{nombre}")
    public Cliente updateEntregado(@PathVariable String nombre) {
        return clienteService.updateEntregado(nombre);
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> deleteCliente(@PathVariable String nombre) {
        return clienteService.deleteCliente(nombre);
    }
}
