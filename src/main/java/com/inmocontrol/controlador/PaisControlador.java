package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.PaisDTO;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.negocio.fachada.pais.ConsultarPaisPorFiltrosFachada;
import com.inmocontrol.negocio.fachada.pais.ConsultarPaisPorIdFachada;
import com.inmocontrol.negocio.fachada.pais.ConsultarPaisTodosFachada;
import com.inmocontrol.negocio.fachada.pais.impl.ConsultarPaisPorFiltrosFachadaImpl;
import com.inmocontrol.negocio.fachada.pais.impl.ConsultarPaisPorIdFachadaImpl;
import com.inmocontrol.negocio.fachada.pais.impl.ConsultarPaisTodosFachadaImpl;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paises")
public class PaisControlador {

	@GetMapping
	public ResponseEntity<RespuestaExito<List<PaisEntidad>>> consultarTodos() {
		ConsultarPaisTodosFachada fachada = new ConsultarPaisTodosFachadaImpl();
		List<PaisEntidad> resultado = fachada.ejecutar(null);
		return ResponseEntity.ok(RespuestaExito.crear("Paises obtenidos exitosamente", resultado));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaExito<PaisEntidad>> consultarPorId(@PathVariable UUID id) {
		PaisDTO dto = new PaisDTO.Builder().id(id).build();
		ConsultarPaisPorIdFachada fachada = new ConsultarPaisPorIdFachadaImpl();
		PaisEntidad resultado = fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Pais obtenido exitosamente", resultado));
	}

	@GetMapping("/buscar")
	public ResponseEntity<RespuestaExito<List<PaisEntidad>>> consultarPorFiltros(
			@RequestParam(required = false) String nombre) {
		PaisDTO dto = new PaisDTO.Builder().nombre(nombre).build();
		ConsultarPaisPorFiltrosFachada fachada = new ConsultarPaisPorFiltrosFachadaImpl();
		List<PaisEntidad> resultado = fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Paises filtrados obtenidos exitosamente", resultado));
	}
}