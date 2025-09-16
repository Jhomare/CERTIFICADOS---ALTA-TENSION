package com.certificaciones.service;

import com.certificaciones.model.*;
import com.certificaciones.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ExamenMedicoRepository examenMedicoRepository;

    @Autowired
    private ProtocoloRepository protocoloRepository;

    @Autowired
    private CertificadoRepository certificadoRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.notification.email}")
    private String emailDestino;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Ejecutar todos los días a las 11:00 AM
    @Scheduled(cron = "0 30 09 * * ?")
    public void verificarVencimientos() {
        LocalDate hoy = LocalDate.now();
        LocalDate unaSemana = hoy.plusDays(7);
        LocalDate unDia = hoy.plusDays(1);

        // Verificar vencimientos de una semana
        verificarCursosUnaSemana(hoy, unaSemana);
        verificarExamenesUnaSemana(hoy, unaSemana);
        verificarProtocolosUnaSemana(hoy, unaSemana);
        verificarCertificadosUnaSemana(hoy, unaSemana);

        // Verificar vencimientos de un día
        verificarCursosUnDia(hoy, unDia);
        verificarExamenesUnDia(hoy, unDia);
        verificarProtocolosUnDia(hoy, unDia);
        verificarCertificadosUnDia(hoy, unDia);
    }

    private void verificarCursosUnaSemana(LocalDate hoy, LocalDate unaSemana) {
        List<Curso> cursos = cursoRepository.findCursosPorVencer(hoy, unaSemana);
        for (Curso curso : cursos) {
            if (curso.getFechaCaducidad().equals(unaSemana)) {
                enviarNotificacionCurso(curso, "1 semana");
            }
        }
    }

    private void verificarCursosUnDia(LocalDate hoy, LocalDate unDia) {
        List<Curso> cursos = cursoRepository.findCursosPorVencer(hoy, unDia);
        for (Curso curso : cursos) {
            if (curso.getFechaCaducidad().equals(unDia)) {
                enviarNotificacionCurso(curso, "1 día");
            }
        }
    }

    private void verificarExamenesUnaSemana(LocalDate hoy, LocalDate unaSemana) {
        List<ExamenMedico> examenes = examenMedicoRepository.findExamenesPorVencer(hoy, unaSemana);
        for (ExamenMedico examen : examenes) {
            if (examen.getFechaCaducidad().equals(unaSemana)) {
                enviarNotificacionExamen(examen, "1 semana");
            }
        }
    }

    private void verificarExamenesUnDia(LocalDate hoy, LocalDate unDia) {
        List<ExamenMedico> examenes = examenMedicoRepository.findExamenesPorVencer(hoy, unDia);
        for (ExamenMedico examen : examenes) {
            if (examen.getFechaCaducidad().equals(unDia)) {
                enviarNotificacionExamen(examen, "1 día");
            }
        }
    }

    private void verificarProtocolosUnaSemana(LocalDate hoy, LocalDate unaSemana) {
        List<ProtocoloPrueba> protocolos = protocoloRepository.findProtocolosPorVencer(hoy, unaSemana);
        for (ProtocoloPrueba protocolo : protocolos) {
            if (protocolo.getFechaCaducidad().equals(unaSemana)) {
                enviarNotificacionProtocolo(protocolo, "1 semana");
            }
        }
    }

    private void verificarProtocolosUnDia(LocalDate hoy, LocalDate unDia) {
        List<ProtocoloPrueba> protocolos = protocoloRepository.findProtocolosPorVencer(hoy, unDia);
        for (ProtocoloPrueba protocolo : protocolos) {
            if (protocolo.getFechaCaducidad().equals(unDia)) {
                enviarNotificacionProtocolo(protocolo, "1 día");
            }
        }
    }

    private void verificarCertificadosUnaSemana(LocalDate hoy, LocalDate unaSemana) {
        List<CertificadoOperatividad> certificados = certificadoRepository.findCertificadosPorVencer(hoy, unaSemana);
        for (CertificadoOperatividad certificado : certificados) {
            if (certificado.getFechaCaducidad().equals(unaSemana)) {
                enviarNotificacionCertificado(certificado, "1 semana");
            }
        }
    }

    private void verificarCertificadosUnDia(LocalDate hoy, LocalDate unDia) {
        List<CertificadoOperatividad> certificados = certificadoRepository.findCertificadosPorVencer(hoy, unDia);
        for (CertificadoOperatividad certificado : certificados) {
            if (certificado.getFechaCaducidad().equals(unDia)) {
                enviarNotificacionCertificado(certificado, "1 día");
            }
        }
    }

    private void enviarNotificacionCurso(Curso curso, String periodo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(emailDestino);
        mensaje.setSubject("Alerta de Vencimiento - Curso");
        mensaje.setText(String.format(
                "El curso %s de %s %s (DNI: %s) está por vencer en %s.\n" +
                        "Fecha de vencimiento: %s",
                curso.getTipoCurso().getDescripcion(),
                curso.getNombre(),
                curso.getApellidos(),
                curso.getDni(),
                periodo,
                curso.getFechaCaducidad().format(formatter)
        ));
        mailSender.send(mensaje);
    }

    private void enviarNotificacionExamen(ExamenMedico examen, String periodo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(emailDestino);
        mensaje.setSubject("Alerta de Vencimiento - Examen Médico");
        mensaje.setText(String.format(
                "El examen médico de %s %s (DNI: %s) está por vencer en %s.\n" +
                        "Fecha de vencimiento: %s",
                examen.getNombre(),
                examen.getApellidos(),
                examen.getDni(),
                periodo,
                examen.getFechaCaducidad().format(formatter)
        ));
        mailSender.send(mensaje);
    }

    private void enviarNotificacionProtocolo(ProtocoloPrueba protocolo, String periodo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(emailDestino);
        mensaje.setSubject("Alerta de Vencimiento - Protocolo de Prueba");
        mensaje.setText(String.format(
                "El protocolo de prueba para %s está por vencer en %s.\n" +
                        "Fecha de vencimiento: %s",
                protocolo.getElemento().getDescripcion(),
                periodo,
                protocolo.getFechaCaducidad().format(formatter)
        ));
        mailSender.send(mensaje);
    }

    private void enviarNotificacionCertificado(CertificadoOperatividad certificado, String periodo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(emailDestino);
        mensaje.setSubject("Alerta de Vencimiento - Certificado de Operatividad");
        mensaje.setText(String.format(
                "El certificado de operatividad del equipo %s está por vencer en %s.\n" +
                        "Fecha de vencimiento: %s",
                certificado.getNombreEquipo(),
                periodo,
                certificado.getFechaCaducidad().format(formatter)
        ));
        mailSender.send(mensaje);
    }
}