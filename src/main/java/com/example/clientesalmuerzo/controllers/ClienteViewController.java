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

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "formulario";
    }

    @PostMapping
    public String guardarCliente(@ModelAttribute Cliente cliente, Model model) {
        var response = clienteService.addCliente(cliente);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/clientes/lista";
        } else {
            model.addAttribute("error", "El cliente ya existe");
            model.addAttribute("cliente", cliente);
            return "formulario";
        }
    }

    // Alternar pago usando PUT
    @PutMapping("/{nombre}")
    public String alternarPago(@PathVariable String nombre) {
        clienteService.updatePago(nombre);
        return "redirect:/clientes/lista";
    }

    @PutMapping("/entregado/{nombre}")
    public String alternarEntregado(@PathVariable String nombre) {
        clienteService.updateEntregado(nombre);
        return "redirect:/clientes/lista";
    }

    // Eliminar cliente usando DELETE
    @DeleteMapping("/{nombre}")
    public String eliminarCliente(@PathVariable String nombre) {
        clienteService.deleteCliente(nombre);
        return "redirect:/clientes/lista";
    }

    @GetMapping("/lista")
    public String mostrarLista(Model model) {
        model.addAttribute("clientes", clienteService.getClientes());
        return "lista";
    }

    @GetMapping("/totales")
    public String mostrarTotales(Model model) {
        var totales = clienteService.calcularTotales();
        model.addAttribute("totalPagado", totales.getTotalPagado());
        model.addAttribute("totalNoPagado", totales.getTotalNoPagado());
        return "totales";
    }
}
