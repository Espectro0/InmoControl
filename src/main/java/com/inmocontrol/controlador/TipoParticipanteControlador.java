package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.TipoParticipanteDTO;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.fachada.tipoparticipante.*;
import com.inmocontrol.negocio.fachada.tipoparticipante.impl.*;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tipos-participante")
public class TipoParticipanteControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<TipoParticipanteEntidad>> consultarPorId(
      @PathVariable UUID id) {
    TipoParticipanteDTO dto = new TipoParticipanteDTO.Builder().id(id).build();
    ConsultarTipoParticipantePorIdFachada fachada = new ConsultarTipoParticipantePorIdFachadaImpl();
    TipoParticipanteEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Tipo de participante obtenido exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<TipoParticipanteEntidad>>> consultar(
      @RequestParam(required = false) String nombre) {

    TipoParticipanteDTO dto = new TipoParticipanteDTO.Builder().nombre(nombre).build();

    List<TipoParticipanteEntidad> resultado;
    boolean tieneFiltros = nombre != null;

    if (tieneFiltros) {
      ConsultarTipoParticipantePorFiltrosFachada fachadaFiltros =
          new ConsultarTipoParticipantePorFiltrosFachadaImpl();
      resultado = fachadaFiltros.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear(
              "Tipos de participante filtrados obtenidos exitosamente", resultado));
    } else {
      ConsultarTipoParticipanteTodosFachada fachadaTodos =
          new ConsultarTipoParticipanteTodosFachadaImpl();
      resultado = fachadaTodos.ejecutar();
      return ResponseEntity.ok(
          RespuestaExito.crear("Tipos de participante obtenidos exitosamente", resultado));
    }
  }

  @PostMapping
  public ResponseEntity<RespuestaExito<Void>> registrar(@RequestBody TipoParticipanteDTO dto) {
    RegistrarTipoParticipanteFachada fachada = new RegistrarTipoParticipanteFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.status(201)
        .body(RespuestaExito.crear("Tipo de participante registrado exitosamente"));
  }

  @PutMapping
  public ResponseEntity<RespuestaExito<TipoParticipanteEntidad>> modificar(
      @RequestBody TipoParticipanteDTO dto) {
    ModificarTipoParticipanteFachada fachada = new ModificarTipoParticipanteFachadaImpl();
    TipoParticipanteEntidad resultado = new TipoParticipanteEntidad.Builder().build();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Tipo de participante modificado exitosamente", resultado));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<RespuestaExito<Void>> eliminar(@PathVariable UUID id) {
    TipoParticipanteDTO dto = new TipoParticipanteDTO.Builder().id(id).build();
    EliminarTipoParticipanteFachada fachada = new EliminarTipoParticipanteFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Tipo de participante eliminado exitosamente"));
  }
}
