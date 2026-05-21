package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.ClausulaPorContratoDTO;
import com.inmocontrol.dto.ParametroClausulaContratoDTO;
import com.inmocontrol.dto.ParametroDTO;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.fachada.parametroclausulacontrato.*;
import com.inmocontrol.negocio.fachada.parametroclausulacontrato.impl.*;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parametros-clausula-contrato")
public class ParametroClausulaContratoControlador {

	@GetMapping
	public ResponseEntity<RespuestaExito<List<ParametroClausulaContratoEntidad>>> consultarTodos() {
		ConsultarParametroClausulaContratoTodosFachada fachada = new ConsultarParametroClausulaContratoTodosFachadaImpl();
		List<ParametroClausulaContratoEntidad> resultado = fachada.ejecutar(null);
		return ResponseEntity
				.ok(RespuestaExito.crear("Parametros Clausula Contrato obtenidos exitosamente", resultado));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaExito<ParametroClausulaContratoEntidad>> consultarPorId(@PathVariable UUID id) {
		ParametroClausulaContratoDTO dto = new ParametroClausulaContratoDTO.Builder().id(id).build();
		ConsultarParametroClausulaContratoPorIdFachada fachada = new ConsultarParametroClausulaContratoPorIdFachadaImpl();
		ParametroClausulaContratoEntidad resultado = fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Parametro Clausula Contrato obtenido exitosamente", resultado));
	}

	@GetMapping("/buscar")
	public ResponseEntity<RespuestaExito<List<ParametroClausulaContratoEntidad>>> consultarPorFiltros(
			@RequestParam(required = false) UUID parametroId,
			@RequestParam(required = false) UUID clausulaPorContratoId, @RequestParam(required = false) String valor) {
		ParametroClausulaContratoDTO dto = new ParametroClausulaContratoDTO.Builder()
				.parametro(parametroId != null ? new ParametroDTO.Builder().id(parametroId).build() : null)
				.clausulaPorContrato(clausulaPorContratoId != null
						? new ClausulaPorContratoDTO.Builder().id(clausulaPorContratoId).build()
						: null)
				.valor(valor).build();
		ConsultarParametroClausulaContratoPorFiltrosFachada fachada = new ConsultarParametroClausulaContratoPorFiltrosFachadaImpl();
		List<ParametroClausulaContratoEntidad> resultado = fachada.ejecutar(dto);
		return ResponseEntity
				.ok(RespuestaExito.crear("Parametros Clausula Contrato filtrados obtenidos exitosamente", resultado));
	}

	@PostMapping
	public ResponseEntity<RespuestaExito<ParametroClausulaContratoEntidad>> registrar(@RequestBody ParametroClausulaContratoDTO dto) {
		RegistrarParametroClausulaContratoFachada fachada = new RegistrarParametroClausulaContratoFachadaImpl();
		ParametroClausulaContratoEntidad resultado = new ParametroClausulaContratoEntidad.Builder().build();
		fachada.ejecutar(dto);
		return ResponseEntity.status(201).body(RespuestaExito.crear("Parametro Clausula Contrato registrado exitosamente", resultado));
	}

	@PutMapping
	public ResponseEntity<RespuestaExito<ParametroClausulaContratoEntidad>> modificar(@RequestBody ParametroClausulaContratoDTO dto) {
		ModificarParametroClausulaContratoFachada fachada = new ModificarParametroClausulaContratoFachadaImpl();
		ParametroClausulaContratoEntidad resultado = new ParametroClausulaContratoEntidad.Builder().build();
		fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Parametro Clausula Contrato modificado exitosamente", resultado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RespuestaExito<Void>> eliminar(@PathVariable UUID id) {
		ParametroClausulaContratoDTO dto = new ParametroClausulaContratoDTO.Builder().id(id).build();
		EliminarParametroClausulaContratoFachada fachada = new EliminarParametroClausulaContratoFachadaImpl();
		fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Parametro Clausula Contrato eliminado exitosamente", null));
	}
}