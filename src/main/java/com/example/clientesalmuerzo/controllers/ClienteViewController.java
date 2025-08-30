package com.example.clientesalmuerzo.controllers;

import com.example.clientesalmuerzo.domain.Cliente;
import com.example.clientesalmuerzo.services.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteViewController {

    private final ClienteService clienteService;

    public ClienteViewController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Mostrar formulario
    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "formulario";
    }

    // Guardar cliente desde formulario
    @PostMapping
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.addCliente(cliente);
        return "redirect:/clientes/lista";
    }

    // Mostrar lista de clientes
    @GetMapping("/lista")
    public String mostrarLista(Model model) {
        model.addAttribute("clientes", clienteService.getClientes());
        return "lista";
    }

    // Alternar pago desde lista
    @PostMapping("/{nombre}")
    public String alternarPago(@PathVariable String nombre) {
        clienteService.updatePago(nombre);
        return "redirect:/clientes/lista";
    }
}
