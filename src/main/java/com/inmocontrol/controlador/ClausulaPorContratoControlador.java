package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.ClausulaContratoDTO;
import com.inmocontrol.dto.ClausulaPorContratoDTO;
import com.inmocontrol.dto.ContratoDTO;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.negocio.fachada.clausulaporcontrato.*;
import com.inmocontrol.negocio.fachada.clausulaporcontrato.impl.*;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clausulas-por-contrato")
public class ClausulaPorContratoControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<ClausulaPorContratoEntidad>> consultarPorId(
      @PathVariable UUID id) {
    ClausulaPorContratoDTO dto = new ClausulaPorContratoDTO.Builder().id(id).build();
    ConsultarClausulaPorContratoPorIdFachada fachada =
        new ConsultarClausulaPorContratoPorIdFachadaImpl();
    ClausulaPorContratoEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Clausula por contrato obtenida exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<ClausulaPorContratoEntidad>>> consultar(
      @RequestParam(required = false) Integer numeroClausula,
      @RequestParam(required = false) UUID contratoId,
      @RequestParam(required = false) UUID clausulaId) {

    ClausulaPorContratoDTO dto =
        new ClausulaPorContratoDTO.Builder()
            .numeroClausula(numeroClausula)
            .contrato(contratoId != null ? new ContratoDTO.Builder().id(contratoId).build() : null)
            .clausula(
                clausulaId != null
                    ? new ClausulaContratoDTO.Builder().id(clausulaId).build()
                    : null)
            .build();

    List<ClausulaPorContratoEntidad> resultado;
    boolean tieneFiltros = numeroClausula != null || contratoId != null || clausulaId != null;

    if (tieneFiltros) {
      ConsultarClausulaPorContratoPorFiltrosFachada fachadaFiltros =
          new ConsultarClausulaPorContratoPorFiltrosFachadaImpl();
      resultado = fachadaFiltros.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear(
              "Clausulas por contrato filtradas obtenidas exitosamente", resultado));
    } else {
      ConsultarClausulaPorContratoTodosFachada fachadaTodos =
          new ConsultarClausulaPorContratoTodosFachadaImpl();
      resultado = fachadaTodos.ejecutar();
      return ResponseEntity.ok(
          RespuestaExito.crear("Clausulas por contrato obtenidas exitosamente", resultado));
    }
  }

  @PostMapping
  public ResponseEntity<RespuestaExito<ClausulaPorContratoEntidad>> registrar(
      @RequestBody ClausulaPorContratoDTO dto) {
    RegistrarClausulaPorContratoFachada fachada = new RegistrarClausulaPorContratoFachadaImpl();
    ClausulaPorContratoEntidad resultado = new ClausulaPorContratoEntidad.Builder().build();
    fachada.ejecutar(dto);
    return ResponseEntity.status(201)
        .body(RespuestaExito.crear("Clausula por contrato registrada exitosamente", resultado));
  }

  @PutMapping
  public ResponseEntity<RespuestaExito<ClausulaPorContratoEntidad>> modificar(
      @RequestBody ClausulaPorContratoDTO dto) {
    ModificarClausulaPorContratoFachada fachada = new ModificarClausulaPorContratoFachadaImpl();
    ClausulaPorContratoEntidad resultado = new ClausulaPorContratoEntidad.Builder().build();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Clausula por contrato modificada exitosamente", resultado));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<RespuestaExito<Void>> eliminar(@PathVariable UUID id) {
    ClausulaPorContratoDTO dto = new ClausulaPorContratoDTO.Builder().id(id).build();
    EliminarClausulaPorContratoFachada fachada = new EliminarClausulaPorContratoFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Clausula por contrato eliminada exitosamente", null));
  }
}
