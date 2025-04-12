package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private int capacidad;
    private boolean disponible;

    public Mesa() {}

    public Mesa(int numero, int capacidad, boolean disponible) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.disponible = disponible;
    }

    public Long getId() { return id; }
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}  