package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.TipoDocumentoDTO;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.fachada.tipodocumento.ConsultarTipoDocumentoPorFiltrosFachada;
import com.inmocontrol.negocio.fachada.tipodocumento.ConsultarTipoDocumentoPorIdFachada;
import com.inmocontrol.negocio.fachada.tipodocumento.ConsultarTipoDocumentoTodosFachada;
import com.inmocontrol.negocio.fachada.tipodocumento.impl.ConsultarTipoDocumentoPorFiltrosFachadaImpl;
import com.inmocontrol.negocio.fachada.tipodocumento.impl.ConsultarTipoDocumentoPorIdFachadaImpl;
import com.inmocontrol.negocio.fachada.tipodocumento.impl.ConsultarTipoDocumentoTodosFachadaImpl;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tipos-documento")
public class TipoDocumentoControlador {

	@GetMapping
	public ResponseEntity<RespuestaExito<List<TipoDocumentoEntidad>>> consultarTodos() {
		ConsultarTipoDocumentoTodosFachada fachada = new ConsultarTipoDocumentoTodosFachadaImpl();
		List<TipoDocumentoEntidad> resultado = fachada.ejecutar(null);
		return ResponseEntity.ok(RespuestaExito.crear("Tipos de documento obtenidos exitosamente", resultado));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaExito<TipoDocumentoEntidad>> consultarPorId(@PathVariable UUID id) {
		TipoDocumentoDTO dto = new TipoDocumentoDTO.Builder().id(id).build();
		ConsultarTipoDocumentoPorIdFachada fachada = new ConsultarTipoDocumentoPorIdFachadaImpl();
		TipoDocumentoEntidad resultado = fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Tipo de documento obtenido exitosamente", resultado));
	}

	@GetMapping("/buscar")
	public ResponseEntity<RespuestaExito<List<TipoDocumentoEntidad>>> consultarPorFiltros(
			@RequestParam(required = false) String nombre) {
		TipoDocumentoDTO dto = new TipoDocumentoDTO.Builder().nombre(nombre).build();
		ConsultarTipoDocumentoPorFiltrosFachada fachada = new ConsultarTipoDocumentoPorFiltrosFachadaImpl();
		List<TipoDocumentoEntidad> resultado = fachada.ejecutar(dto);
		return ResponseEntity
				.ok(RespuestaExito.crear("Tipos de documento filtrados obtenidos exitosamente", resultado));
	}
}