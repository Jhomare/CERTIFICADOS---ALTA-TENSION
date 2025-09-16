package com.certificaciones.repository;

import com.certificaciones.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByDni(String dni);

    @Query("SELECT c FROM Curso c WHERE c.fechaCaducidad BETWEEN :fechaInicio AND :fechaFin")
    List<Curso> findCursosPorVencer(@Param("fechaInicio") LocalDate fechaInicio,
                                    @Param("fechaFin") LocalDate fechaFin);
}