package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.TipoAplicacionDTO;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.negocio.fachada.tipoaplicacion.ConsultarTipoAplicacionPorFiltrosFachada;
import com.inmocontrol.negocio.fachada.tipoaplicacion.ConsultarTipoAplicacionPorIdFachada;
import com.inmocontrol.negocio.fachada.tipoaplicacion.ConsultarTipoAplicacionTodosFachada;
import com.inmocontrol.negocio.fachada.tipoaplicacion.impl.ConsultarTipoAplicacionPorFiltrosFachadaImpl;
import com.inmocontrol.negocio.fachada.tipoaplicacion.impl.ConsultarTipoAplicacionPorIdFachadaImpl;
import com.inmocontrol.negocio.fachada.tipoaplicacion.impl.ConsultarTipoAplicacionTodosFachadaImpl;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tipos-aplicacion")
public class TipoAplicacionControlador {

	@GetMapping
	public ResponseEntity<RespuestaExito<List<TipoAplicacionEntidad>>> consultarTodos() {
		ConsultarTipoAplicacionTodosFachada fachada = new ConsultarTipoAplicacionTodosFachadaImpl();
		List<TipoAplicacionEntidad> resultado = fachada.ejecutar(null);
		return ResponseEntity.ok(RespuestaExito.crear("Tipos de aplicacion obtenidos exitosamente", resultado));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaExito<TipoAplicacionEntidad>> consultarPorId(@PathVariable UUID id) {
		TipoAplicacionDTO dto = new TipoAplicacionDTO.Builder().id(id).build();
		ConsultarTipoAplicacionPorIdFachada fachada = new ConsultarTipoAplicacionPorIdFachadaImpl();
		TipoAplicacionEntidad resultado = fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Tipo de aplicacion obtenido exitosamente", resultado));
	}

	@GetMapping("/buscar")
	public ResponseEntity<RespuestaExito<List<TipoAplicacionEntidad>>> consultarPorFiltros(
			@RequestParam(required = false) String nombre) {
		TipoAplicacionDTO dto = new TipoAplicacionDTO.Builder().nombre(nombre).build();
		ConsultarTipoAplicacionPorFiltrosFachada fachada = new ConsultarTipoAplicacionPorFiltrosFachadaImpl();
		List<TipoAplicacionEntidad> resultado = fachada.ejecutar(dto);
		return ResponseEntity
				.ok(RespuestaExito.crear("Tipos de aplicacion filtrados obtenidos exitosamente", resultado));
	}
}