package com.example.clientesalmuerzo.controllers;

import com.example.clientesalmuerzo.domain.Cliente;
import com.example.clientesalmuerzo.services.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes") // Rutas para la interfaz web
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
    public String guardarCliente(@ModelAttribute Cliente cliente, Model model) {
        var response = clienteService.addCliente(cliente);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/clientes/lista";
        } else {
            // Si ya existe, mostrar mensaje en formulario
            model.addAttribute("error", "El cliente ya existe");
            model.addAttribute("cliente", cliente);
            return "formulario";
        }
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