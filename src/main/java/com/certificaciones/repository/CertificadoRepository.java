package com.certificaciones.repository;

import com.certificaciones.model.CertificadoOperatividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CertificadoRepository extends JpaRepository<CertificadoOperatividad, Long> {
    @Query("SELECT c FROM CertificadoOperatividad c WHERE c.fechaCaducidad BETWEEN :fechaInicio AND :fechaFin")
    List<CertificadoOperatividad> findCertificadosPorVencer(@Param("fechaInicio") LocalDate fechaInicio,
                                                            @Param("fechaFin") LocalDate fechaFin);
}