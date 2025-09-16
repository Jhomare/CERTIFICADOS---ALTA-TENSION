package com.certificaciones.model;

public enum TipoCurso {
    ALTURA_BASICO("Altura Nivel Básico"),
    ALTURA_AVANZADO("Altura Nivel Avanzado"),
    TRABAJOS_CALIENTE("Trabajos en Caliente"),
    EQUIPOS_PODER("Equipos de Poder"),
    ANDAMIOS("Armado y Desarmado de Andamios"),
    INTEGRAL_SEGURIDAD("Curso Integral de Seguridad y Riesgos Eléctricos"),
    SEGURIDAD_AVANZADO("Curso de Seguridad Avanzado"),
    ESPACIOS_CONFINADOS("Espacios Confinados");

    private final String descripcion;

    TipoCurso(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}