package com.certificaciones.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String dni;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCurso tipoCurso;

    @Column(nullable = false)
    private LocalDate fechaRealizacion;

    @Column(nullable = false)
    private LocalDate fechaCaducidad;

    // Constructores
    public Curso() {}

    public Curso(String dni, String nombre, String apellidos, TipoCurso tipoCurso,
                 LocalDate fechaRealizacion, LocalDate fechaCaducidad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tipoCurso = tipoCurso;
        this.fechaRealizacion = fechaRealizacion;
        this.fechaCaducidad = fechaCaducidad;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public TipoCurso getTipoCurso() { return tipoCurso; }
    public void setTipoCurso(TipoCurso tipoCurso) { this.tipoCurso = tipoCurso; }

    public LocalDate getFechaRealizacion() { return fechaRealizacion; }
    public void setFechaRealizacion(LocalDate fechaRealizacion) { this.fechaRealizacion = fechaRealizacion; }

    public LocalDate getFechaCaducidad() { return fechaCaducidad; }
    public void setFechaCaducidad(LocalDate fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }
}