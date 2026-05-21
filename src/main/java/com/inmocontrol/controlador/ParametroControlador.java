package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.ParametroDTO;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.fachada.parametro.*;
import com.inmocontrol.negocio.fachada.parametro.impl.*;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parametros")
public class ParametroControlador {

	@GetMapping
	public ResponseEntity<RespuestaExito<List<ParametroEntidad>>> consultarTodos() {
		ConsultarParametroTodosFachada fachada = new ConsultarParametroTodosFachadaImpl();
		List<ParametroEntidad> resultado = fachada.ejecutar(null);
		return ResponseEntity.ok(RespuestaExito.crear("Parametros obtenidos exitosamente", resultado));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaExito<ParametroEntidad>> consultarPorId(@PathVariable UUID id) {
		ParametroDTO dto = new ParametroDTO.Builder().id(id).build();
		ConsultarParametroPorIdFachada fachada = new ConsultarParametroPorIdFachadaImpl();
		ParametroEntidad resultado = fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Parametro obtenido exitosamente", resultado));
	}

	@GetMapping("/buscar")
	public ResponseEntity<RespuestaExito<List<ParametroEntidad>>> consultarPorFiltros(
			@RequestParam(required = false) String placeholder, @RequestParam(required = false) String descripcion) {
		ParametroDTO dto = new ParametroDTO.Builder().placeholder(placeholder).descripcion(descripcion).build();
		ConsultarParametroPorFiltrosFachada fachada = new ConsultarParametroPorFiltrosFachadaImpl();
		List<ParametroEntidad> resultado = fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Parametros filtrados obtenidos exitosamente", resultado));
	}

	@PostMapping
	public ResponseEntity<RespuestaExito<ParametroEntidad>> registrar(@RequestBody ParametroDTO dto) {
		RegistrarParametroFachada fachada = new RegistrarParametroFachadaImpl();
		ParametroEntidad resultado = new ParametroEntidad.Builder().build();
		fachada.ejecutar(dto);
		return ResponseEntity.status(201).body(RespuestaExito.crear("Parametro registrado exitosamente", resultado));
	}

	@PutMapping
	public ResponseEntity<RespuestaExito<ParametroEntidad>> modificar(@RequestBody ParametroDTO dto) {
		ModificarParametroFachada fachada = new ModificarParametroFachadaImpl();
		ParametroEntidad resultado = new ParametroEntidad.Builder().build();
		fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Parametro modificado exitosamente", resultado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RespuestaExito<Void>> eliminar(@PathVariable UUID id) {
		ParametroDTO dto = new ParametroDTO.Builder().id(id).build();
		EliminarParametroFachada fachada = new EliminarParametroFachadaImpl();
		fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Parametro eliminado exitosamente", null));
	}
}