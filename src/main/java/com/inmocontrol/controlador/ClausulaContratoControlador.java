package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.AreaReferenciaDTO;
import com.inmocontrol.dto.ClausulaContratoDTO;
import com.inmocontrol.dto.TipoAplicacionDTO;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.negocio.fachada.clausulacontrato.*;
import com.inmocontrol.negocio.fachada.clausulacontrato.impl.*;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clausulas-contrato")
public class ClausulaContratoControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<ClausulaContratoEntidad>> consultarPorId(
      @PathVariable UUID id) {
    ClausulaContratoDTO dto = new ClausulaContratoDTO.Builder().id(id).build();
    ConsultarClausulaContratoPorIdFachada fachada = new ConsultarClausulaContratoPorIdFachadaImpl();
    ClausulaContratoEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Clausula de contrato obtenida exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<ClausulaContratoEntidad>>> consultar(
      @RequestParam(required = false) String titulo,
      @RequestParam(required = false) UUID areaReferenciaId,
      @RequestParam(required = false) UUID tipoAplicacionId) {

    ClausulaContratoDTO dto =
        new ClausulaContratoDTO.Builder()
            .titulo(titulo)
            .areaReferencia(
                areaReferenciaId != null
                    ? new AreaReferenciaDTO.Builder().id(areaReferenciaId).build()
                    : null)
            .tipoAplicacion(
                tipoAplicacionId != null
                    ? new TipoAplicacionDTO.Builder().id(tipoAplicacionId).build()
                    : null)
            .build();

    List<ClausulaContratoEntidad> resultado;
    boolean tieneFiltros = titulo != null || areaReferenciaId != null || tipoAplicacionId != null;

    if (tieneFiltros) {
      ConsultarClausulaContratoPorFiltrosFachada fachadaFiltros =
          new ConsultarClausulaContratoPorFiltrosFachadaImpl();
      resultado = fachadaFiltros.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear(
              "Clausulas de contrato filtradas obtenidas exitosamente", resultado));
    } else {
      ConsultarClausulaContratoTodosFachada fachadaTodos =
          new ConsultarClausulaContratoTodosFachadaImpl();
      resultado = fachadaTodos.ejecutar();
      return ResponseEntity.ok(
          RespuestaExito.crear("Clausulas de contrato obtenidas exitosamente", resultado));
    }
  }

  @PostMapping
  public ResponseEntity<RespuestaExito<Void>> registrar(@RequestBody ClausulaContratoDTO dto) {
    RegistrarClausulaContratoFachada fachada = new RegistrarClausulaContratoFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.status(201)
        .body(RespuestaExito.crear("Clausula de contrato registrada exitosamente"));
  }

  @PutMapping
  public ResponseEntity<RespuestaExito<ClausulaContratoEntidad>> modificar(
      @RequestBody ClausulaContratoDTO dto) {
    ModificarClausulaContratoFachada fachada = new ModificarClausulaContratoFachadaImpl();
    ClausulaContratoEntidad resultado = new ClausulaContratoEntidad.Builder().build();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Clausula de contrato modificada exitosamente", resultado));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<RespuestaExito<Void>> eliminar(@PathVariable UUID id) {
    ClausulaContratoDTO dto = new ClausulaContratoDTO.Builder().id(id).build();
    EliminarClausulaContratoFachada fachada = new EliminarClausulaContratoFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Clausula de contrato eliminada exitosamente"));
  }
}
