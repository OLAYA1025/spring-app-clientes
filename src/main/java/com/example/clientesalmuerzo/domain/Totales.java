package com.example.clientesalmuerzo.domain;

public class Totales {
    private int totalPagado;
    private int totalNoPagado;
    private int totalEntregado;
    private int totalNoEntregado;

    public Totales(int totalPagado, int totalNoPagado, int totalEntregado, int totalNoEntregado) {
        this.totalPagado = totalPagado;
        this.totalNoPagado = totalNoPagado;
        this.totalEntregado = totalEntregado;
        this.totalNoEntregado = totalNoEntregado;
    }

    public int getTotalPagado() {
        return totalPagado;
    }

    public int getTotalNoPagado() {
        return totalNoPagado;
    }

    public void setTotalPagado(int totalPagado) {
        this.totalPagado = totalPagado;
    }

    public int getTotalNoEntregado() {
        return totalNoEntregado;
    }

    public void setTotalNoEntregado(int totalNoEntregado) {
        this.totalNoEntregado = totalNoEntregado;
    }

    public int getTotalEntregado() {
        return totalEntregado;
    }

    public void setTotalEntregado(int totalEntregado) {
        this.totalEntregado = totalEntregado;
    }

    public void setTotalNoPagado(int totalNoPagado) {
        this.totalNoPagado = totalNoPagado;
    }
}