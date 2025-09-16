package com.certificaciones.repository;

import com.certificaciones.model.ExamenMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamenMedicoRepository extends JpaRepository<ExamenMedico, Long> {
    List<ExamenMedico> findByDni(String dni);

    @Query("SELECT e FROM ExamenMedico e WHERE e.fechaCaducidad BETWEEN :fechaInicio AND :fechaFin")
    List<ExamenMedico> findExamenesPorVencer(@Param("fechaInicio") LocalDate fechaInicio,
                                             @Param("fechaFin") LocalDate fechaFin);
}