package com.example.clientesalmuerzo.services;

import com.example.clientesalmuerzo.domain.Cliente;
import com.example.clientesalmuerzo.domain.Totales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {



    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getClientes() {
            return clienteRepository.findAll();
    }

    public ResponseEntity<Cliente> getCliente(String nombre) {
        Optional<Cliente> cliente = clienteRepository.findById(nombre);
        return ResponseEntity.of(cliente);
    }

    public ResponseEntity<?> addCliente(Cliente cliente) {
        Optional<Cliente> respuesta = clienteRepository.findById(cliente.getNombre());
        if (respuesta.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("el cliente ya existe");
        }
        cliente.setEntregado("no");
        cliente.setPago("no");
        clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    public Cliente updatePago(String nombre) {
        Cliente cliente = clienteRepository.findById(nombre)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        if ("si".equalsIgnoreCase(cliente.getPago())) {
            cliente.setPago("no");
        } else {
            cliente.setPago("si");
        }

        return clienteRepository.save(cliente);

    }

    public Cliente updateEntregado(String nombre) {
        Cliente cliente = clienteRepository.findById(nombre)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setEntregado("si");
        return clienteRepository.save(cliente);

    }

    public ResponseEntity<?> deleteCliente(String nombre) {
        Optional<Cliente> respuesta = clienteRepository.findById(nombre);
        if (respuesta.isPresent()) {
            clienteRepository.deleteById(nombre);
            return ResponseEntity.ok().body(respuesta.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("el producto no existe");
    }


    public Totales calcularTotales() {
        int totalPagado = 0;
        int totalNoPagado = 0;
        int totalEntregado = 0;
        int totalNoEntregado = 0;
        for (Cliente c : clienteRepository.findAll()) {
            if (c.getEntregado().equals("si")) {
                totalEntregado+= c.getCantidad();
            }else{
                totalNoEntregado+= c.getCantidad();
            }
            if ("si".equalsIgnoreCase(c.getPago())) {
                totalPagado += c.getCantidad()*25000;
            } else {
                totalNoPagado += c.getCantidad();
            }
        }

        return new Totales(totalPagado, totalNoPagado, totalEntregado, totalNoEntregado);
    }

}
