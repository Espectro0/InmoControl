package com.inmocontrol.controlador.respuesta;

import java.time.LocalDateTime;

import com.inmocontrol.transversal.UtilTexto;

public record RespuestaError(String mensaje, LocalDateTime fecha) {

    public static RespuestaError crear(String mensaje) {
        return new RespuestaError(UtilTexto.aplicarTrim(mensaje), LocalDateTime.now());
    }
}