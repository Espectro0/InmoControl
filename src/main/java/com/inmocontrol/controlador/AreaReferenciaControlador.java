package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.AreaReferenciaDTO;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.negocio.fachada.areareferencia.*;
import com.inmocontrol.negocio.fachada.areareferencia.impl.*;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/areas-referencia")
public class AreaReferenciaControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<AreaReferenciaEntidad>> consultarPorId(
      @PathVariable UUID id) {
    AreaReferenciaDTO dto = new AreaReferenciaDTO.Builder().id(id).build();
    ConsultarAreaReferenciaPorIdFachada fachada = new ConsultarAreaReferenciaPorIdFachadaImpl();
    AreaReferenciaEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Area de referencia obtenida exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<AreaReferenciaEntidad>>> consultar(
      @RequestParam(required = false) String nombre) {

    AreaReferenciaDTO dto = new AreaReferenciaDTO.Builder().nombre(nombre).build();

    List<AreaReferenciaEntidad> resultado;
    boolean tieneFiltros = nombre != null;

    if (tieneFiltros) {
      ConsultarAreaReferenciaPorFiltrosFachada fachadaFiltros =
          new ConsultarAreaReferenciaPorFiltrosFachadaImpl();
      resultado = fachadaFiltros.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear("Areas de referencia filtradas obtenidas exitosamente", resultado));
    } else {
      ConsultarAreaReferenciaTodosFachada fachadaTodos =
          new ConsultarAreaReferenciaTodosFachadaImpl();
      resultado = fachadaTodos.ejecutar();
      return ResponseEntity.ok(
          RespuestaExito.crear("Areas de referencia obtenidas exitosamente", resultado));
    }
  }

  @PostMapping
  public ResponseEntity<RespuestaExito<AreaReferenciaEntidad>> registrar(
      @RequestBody AreaReferenciaDTO dto) {
    RegistrarAreaReferenciaFachada fachada = new RegistrarAreaReferenciaFachadaImpl();
    AreaReferenciaEntidad resultado = new AreaReferenciaEntidad.Builder().build();
    fachada.ejecutar(dto);
    return ResponseEntity.status(201)
        .body(RespuestaExito.crear("Area de referencia registrada exitosamente", resultado));
  }

  @PutMapping
  public ResponseEntity<RespuestaExito<AreaReferenciaEntidad>> modificar(
      @RequestBody AreaReferenciaDTO dto) {
    ModificarAreaReferenciaFachada fachada = new ModificarAreaReferenciaFachadaImpl();
    AreaReferenciaEntidad resultado = new AreaReferenciaEntidad.Builder().build();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Area de referencia modificada exitosamente", resultado));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<RespuestaExito<Void>> eliminar(@PathVariable UUID id) {
    AreaReferenciaDTO dto = new AreaReferenciaDTO.Builder().id(id).build();
    EliminarAreaReferenciaFachada fachada = new EliminarAreaReferenciaFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Area de referencia eliminada exitosamente", null));
  }
}
