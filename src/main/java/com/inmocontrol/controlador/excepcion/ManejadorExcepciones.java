package com.inmocontrol.controlador.excepcion;

import com.inmocontrol.controlador.respuesta.RespuestaError;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ManejadorExcepciones {

	private static final Logger logger = LoggerFactory.getLogger(ManejadorExcepciones.class);

	@ExceptionHandler(InmocontrolExcepcion.class)
	public ResponseEntity<RespuestaError> gestionarInmocontrolExcepcion(InmocontrolExcepcion excepcion) {
		System.err.println(excepcion.getMensajeTecnico());
		if (excepcion.getExcepcionRaiz() != null) {
			excepcion.getExcepcionRaiz().printStackTrace();
		}

		return new ResponseEntity<>(RespuestaError.crear(excepcion.getMensajeUsuario()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<RespuestaError> gestionarTypeMismatch(MethodArgumentTypeMismatchException ex) {
		String param = ex.getName();
		String valorInvalido = ex.getValue() != null ? ex.getValue().toString() : "null";
		String mensaje = String.format("El valor '%s' no es válido para el parámetro '%s'.", valorInvalido, param);

		return new ResponseEntity<>(RespuestaError.crear(mensaje), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<RespuestaError> gestionarNoResource(NoResourceFoundException ex) {
		String mensaje = String.format("Recurso no encontrado: %s", ex.getResourcePath());

		return new ResponseEntity<>(RespuestaError.crear(mensaje), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RespuestaError> gestionarExcepcionGenerica(Exception excepcion) {
		var mensajeUsuario = "Ha ocurrido un error inesperado. Por favor intentelo de nuevo y si el problema persiste contacte a un administrador.";
		var mensajeTecnico = "Se ha presentado una excepción no controlada. Por favor verifique la consola de errores para más detalles.";
		
		logger.error(mensajeTecnico, excepcion);

		return new ResponseEntity<>(RespuestaError.crear(
				mensajeUsuario),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
