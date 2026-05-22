package com.inmocontrol.controlador.respuesta;

import com.inmocontrol.transversal.UtilTexto;
import java.time.LocalDateTime;

public record RespuestaExito<T>(String mensaje, LocalDateTime fecha, T datos) {

  public static <T> RespuestaExito<T> crear(String mensaje, T datos) {
    return new RespuestaExito<T>(UtilTexto.aplicarTrim(mensaje), LocalDateTime.now(), datos);
  }

  public static RespuestaExito<Void> crear(String mensaje) {
    return new RespuestaExito<Void>(UtilTexto.aplicarTrim(mensaje), LocalDateTime.now(), null);
  }
}
