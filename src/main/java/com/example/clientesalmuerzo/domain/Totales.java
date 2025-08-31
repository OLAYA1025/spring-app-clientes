package com.example.clientesalmuerzo.domain;

public class Totales {
    private int totalPagado;
    private int totalNoPagado;

    public Totales(int totalPagado, int totalNoPagado) {
        this.totalPagado = totalPagado;
        this.totalNoPagado = totalNoPagado;
    }

    public int getTotalPagado() {
        return totalPagado;
    }

    public int getTotalNoPagado() {
        return totalNoPagado;
    }
}