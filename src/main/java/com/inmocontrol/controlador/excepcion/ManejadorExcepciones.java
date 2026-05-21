package com.inmocontrol.controlador.excepcion;

import com.inmocontrol.controlador.respuesta.RespuestaError;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejadorExcepciones {

  @ExceptionHandler(InmocontrolExcepcion.class)
  public ResponseEntity<RespuestaError> gestionarInmocontrolExcepcion(
      InmocontrolExcepcion excepcion) {
    System.err.println(excepcion.getMensajeTecnico());
    if (excepcion.getExcepcionRaiz() != null) {
      excepcion.getExcepcionRaiz().printStackTrace();
    }

    return new ResponseEntity<>(
        RespuestaError.crear(excepcion.getMensajeUsuario()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<RespuestaError> gestionarExcepcionGenerica(Exception excepcion) {
    System.err.println("Excepcion no controlada...");
    excepcion.printStackTrace();

    return new ResponseEntity<>(
        RespuestaError.crear(
            "Ha ocurrido un error inesperado. Por favor intentelo de nuevo y si el problema persiste contacte a un administrador."),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
