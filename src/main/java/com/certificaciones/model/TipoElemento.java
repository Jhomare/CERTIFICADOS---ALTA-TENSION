package com.certificaciones.model;

public enum TipoElemento {
    GUANTES_CLASE_0("Guantes Dieléctricos Clase 0"),
    GUANTES_CLASE_3("Guantes Dieléctricos Clase 3"),
    GUANTES_CLASE_4("Guantes Dieléctricos Clase 4"),
    MANTA_DIELECTRICA("Manta Dieléctrica"),
    PERTIGA("Pértiga"),
    REVELADOR("Revelador"),
    PINZA_AMPERIMETRICA("Pinza Amperimétrica");

    private final String descripcion;

    TipoElemento(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}