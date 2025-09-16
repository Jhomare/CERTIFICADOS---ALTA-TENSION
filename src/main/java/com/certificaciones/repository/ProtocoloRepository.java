package com.certificaciones.repository;

import com.certificaciones.model.ProtocoloPrueba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProtocoloRepository extends JpaRepository<ProtocoloPrueba, Long> {
    @Query("SELECT p FROM ProtocoloPrueba p WHERE p.fechaCaducidad BETWEEN :fechaInicio AND :fechaFin")
    List<ProtocoloPrueba> findProtocolosPorVencer(@Param("fechaInicio") LocalDate fechaInicio,
                                                  @Param("fechaFin") LocalDate fechaFin);
}