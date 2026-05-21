package com.inmocontrol.controlador.respuesta;

import com.inmocontrol.transversal.UtilTexto;
import java.time.LocalDateTime;

public record RespuestaError(String mensaje, LocalDateTime fecha) {

  public static RespuestaError crear(String mensaje) {
    return new RespuestaError(UtilTexto.aplicarTrim(mensaje), LocalDateTime.now());
  }
}
