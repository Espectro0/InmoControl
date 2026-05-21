package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.ParticipanteContratoDTO;
import com.inmocontrol.entidad.ParticipanteContratoEntidad;
import com.inmocontrol.negocio.fachada.participantecontrato.*;
import com.inmocontrol.negocio.fachada.participantecontrato.impl.*;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/participantes-contrato")
public class ParticipanteContratoControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<ParticipanteContratoEntidad>> consultarPorId(
      @PathVariable UUID id) {
    ParticipanteContratoDTO dto = new ParticipanteContratoDTO.Builder().id(id).build();
    ConsultarParticipanteContratoPorIdFachada fachada =
        new ConsultarParticipanteContratoPorIdFachadaImpl();
    ParticipanteContratoEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Participante de contrato obtenido exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<ParticipanteContratoEntidad>>> consultar(
      @RequestParam(required = false) UUID personaId,
      @RequestParam(required = false) UUID tipoParticipanteId,
      @RequestParam(required = false) UUID contratoId) {

    ParticipanteContratoDTO dto =
        new ParticipanteContratoDTO.Builder()
            .personaId(personaId)
            .tipoParticipanteId(tipoParticipanteId)
            .contratoId(contratoId)
            .build();

    List<ParticipanteContratoEntidad> resultado;
    boolean tieneFiltros = personaId != null || tipoParticipanteId != null || contratoId != null;

    if (tieneFiltros) {
      ConsultarParticipanteContratoPorFiltrosFachada fachadaFiltros =
          new ConsultarParticipanteContratoPorFiltrosFachadaImpl();
      resultado = fachadaFiltros.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear(
              "Participantes de contrato filtrados obtenidos exitosamente", resultado));
    } else {
      ConsultarParticipanteContratoTodosFachada fachadaTodos =
          new ConsultarParticipanteContratoTodosFachadaImpl();
      resultado = fachadaTodos.ejecutar();
      return ResponseEntity.ok(
          RespuestaExito.crear("Participantes de contrato obtenidos exitosamente", resultado));
    }
  }

  @PostMapping
  public ResponseEntity<RespuestaExito<ParticipanteContratoEntidad>> registrar(
      @RequestBody ParticipanteContratoDTO dto) {
    RegistrarParticipanteContratoFachada fachada = new RegistrarParticipanteContratoFachadaImpl();
    ParticipanteContratoEntidad resultado = new ParticipanteContratoEntidad.Builder().build();
    fachada.ejecutar(dto);
    return ResponseEntity.status(201)
        .body(RespuestaExito.crear("Participante de contrato registrado exitosamente", resultado));
  }

  @PutMapping
  public ResponseEntity<RespuestaExito<ParticipanteContratoEntidad>> modificar(
      @RequestBody ParticipanteContratoDTO dto) {
    ModificarParticipanteContratoFachada fachada = new ModificarParticipanteContratoFachadaImpl();
    ParticipanteContratoEntidad resultado = new ParticipanteContratoEntidad.Builder().build();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Participante de contrato modificado exitosamente", resultado));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<RespuestaExito<Void>> eliminar(@PathVariable UUID id) {
    ParticipanteContratoDTO dto = new ParticipanteContratoDTO.Builder().id(id).build();
    EliminarParticipanteContratoFachada fachada = new EliminarParticipanteContratoFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Participante de contrato eliminado exitosamente", null));
  }
}
