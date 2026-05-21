package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.ParticipanteContratoDTO;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.negocio.fachada.participantecontrato.*;
import com.inmocontrol.negocio.fachada.participantecontrato.impl.*;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participantes-contrato")
public class ParticipanteContratoControlador {

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaExito<ParticipanteContratoEntidad>> consultarPorId(@PathVariable UUID id) {
		ParticipanteContratoDTO dto = new ParticipanteContratoDTO.Builder().id(id).build();
		ConsultarParticipanteContratoPorIdFachada fachada = new ConsultarParticipanteContratoPorIdFachadaImpl();
		ParticipanteContratoEntidad resultado = fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Participante de contrato obtenido exitosamente", resultado));
	}

	@PostMapping
	public ResponseEntity<RespuestaExito<ParticipanteContratoEntidad>> registrar(@RequestBody ParticipanteContratoDTO dto) {
		RegistrarParticipanteContratoFachada fachada = new RegistrarParticipanteContratoFachadaImpl();
		ParticipanteContratoEntidad resultado = new ParticipanteContratoEntidad.Builder().build();
		fachada.ejecutar(dto);
		return ResponseEntity.status(201).body(RespuestaExito.crear("Participante de contrato registrado exitosamente", resultado));
	}

	@PutMapping
	public ResponseEntity<RespuestaExito<ParticipanteContratoEntidad>> modificar(@RequestBody ParticipanteContratoDTO dto) {
		ModificarParticipanteContratoFachada fachada = new ModificarParticipanteContratoFachadaImpl();
		ParticipanteContratoEntidad resultado = new ParticipanteContratoEntidad.Builder().build();
		fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Participante de contrato modificado exitosamente", resultado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<RespuestaExito<Void>> eliminar(@PathVariable UUID id) {
		ParticipanteContratoDTO dto = new ParticipanteContratoDTO.Builder().id(id).build();
		EliminarParticipanteContratoFachada fachada = new EliminarParticipanteContratoFachadaImpl();
		fachada.ejecutar(dto);
		return ResponseEntity.ok(RespuestaExito.crear("Participante de contrato eliminado exitosamente", null));
	}
}