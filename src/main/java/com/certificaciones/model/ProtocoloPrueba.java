package com.certificaciones.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "protocolos_pruebas")
public class ProtocoloPrueba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoElemento elemento;

    @Column(nullable = false)
    private LocalDate fechaRealizacion;

    @Column(nullable = false)
    private LocalDate fechaCaducidad;

    // Constructores
    public ProtocoloPrueba() {}

    public ProtocoloPrueba(TipoElemento elemento, LocalDate fechaRealizacion, LocalDate fechaCaducidad) {
        this.elemento = elemento;
        this.fechaRealizacion = fechaRealizacion;
        this.fechaCaducidad = fechaCaducidad;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TipoElemento getElemento() { return elemento; }
    public void setElemento(TipoElemento elemento) { this.elemento = elemento; }

    public LocalDate getFechaRealizacion() { return fechaRealizacion; }
    public void setFechaRealizacion(LocalDate fechaRealizacion) { this.fechaRealizacion = fechaRealizacion; }

    public LocalDate getFechaCaducidad() { return fechaCaducidad; }
    public void setFechaCaducidad(LocalDate fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }
}