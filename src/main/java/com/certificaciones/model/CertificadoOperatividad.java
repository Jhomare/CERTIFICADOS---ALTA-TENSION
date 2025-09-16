package com.certificaciones.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "certificados_operatividad")
public class CertificadoOperatividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreEquipo;

    @Column(nullable = false)
    private LocalDate fechaRealizacion;

    @Column(nullable = false)
    private LocalDate fechaCaducidad;

    // Constructores
    public CertificadoOperatividad() {}

    public CertificadoOperatividad(String nombreEquipo, LocalDate fechaRealizacion, LocalDate fechaCaducidad) {
        this.nombreEquipo = nombreEquipo;
        this.fechaRealizacion = fechaRealizacion;
        this.fechaCaducidad = fechaCaducidad;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreEquipo() { return nombreEquipo; }
    public void setNombreEquipo(String nombreEquipo) { this.nombreEquipo = nombreEquipo; }

    public LocalDate getFechaRealizacion() { return fechaRealizacion; }
    public void setFechaRealizacion(LocalDate fechaRealizacion) { this.fechaRealizacion = fechaRealizacion; }

    public LocalDate getFechaCaducidad() { return fechaCaducidad; }
    public void setFechaCaducidad(LocalDate fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }
}