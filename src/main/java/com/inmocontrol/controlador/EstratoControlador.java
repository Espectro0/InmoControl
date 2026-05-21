package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.EstratoDTO;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.negocio.fachada.estrato.ConsultarEstratoPorFiltrosFachada;
import com.inmocontrol.negocio.fachada.estrato.ConsultarEstratoPorIdFachada;
import com.inmocontrol.negocio.fachada.estrato.ConsultarEstratoTodosFachada;
import com.inmocontrol.negocio.fachada.estrato.impl.ConsultarEstratoPorFiltrosFachadaImpl;
import com.inmocontrol.negocio.fachada.estrato.impl.ConsultarEstratoPorIdFachadaImpl;
import com.inmocontrol.negocio.fachada.estrato.impl.ConsultarEstratoTodosFachadaImpl;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estratos")
public class EstratoControlador {

	@GetMapping
	public ResponseEntity<RespuestaExito<List<EstratoEntidad>>> consultarTodos() {
		ConsultarEstratoTodosFachada fachada = new ConsultarEstratoTodosFachadaImpl();
		List<EstratoEntidad> resultado = fachada.ejecutar(null);
		return ResponseEntity.ok(RespuestaExito.crear("Estratos obtenidos exitosamente", resultado));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaExito<EstratoEntidad>> consultarPorId(@PathVariable UUID id) {
		EstratoDTO dto = new EstratoDTO.Builder().id(id).build();
		ConsultarEstratoPorIdFachada fachada = new ConsultarEstratoPorIdFachadaImpl();
		EstratoEntidad resultado = fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Estrato obtenido exitosamente", resultado));
	}

	@GetMapping("/buscar")
	public ResponseEntity<RespuestaExito<List<EstratoEntidad>>> consultarPorFiltros(
			@RequestParam(required = false) String nombre, @RequestParam(required = false) String descripcion) {
		EstratoDTO dto = new EstratoDTO.Builder().nombre(nombre).descripcion(descripcion).build();
		ConsultarEstratoPorFiltrosFachada fachada = new ConsultarEstratoPorFiltrosFachadaImpl();
		List<EstratoEntidad> resultado = fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Estratos filtrados obtenidos exitosamente", resultado));
	}
}