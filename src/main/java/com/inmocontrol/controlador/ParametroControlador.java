package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.ParametroDTO;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.fachada.parametro.*;
import com.inmocontrol.negocio.fachada.parametro.impl.*;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parametros")
public class ParametroControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<ParametroEntidad>> consultarPorId(@PathVariable UUID id) {
    ParametroDTO dto = new ParametroDTO.Builder().id(id).build();
    ConsultarParametroPorIdFachada fachada = new ConsultarParametroPorIdFachadaImpl();
    ParametroEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Parametro obtenido exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<ParametroEntidad>>> consultar(
      @RequestParam(required = false) String placeholder,
      @RequestParam(required = false) String descripcion) {

    ParametroDTO dto =
        new ParametroDTO.Builder().placeholder(placeholder).descripcion(descripcion).build();

    List<ParametroEntidad> resultado;
    boolean tieneFiltros = placeholder != null || descripcion != null;

    if (tieneFiltros) {
      ConsultarParametroPorFiltrosFachada fachadaFiltros =
          new ConsultarParametroPorFiltrosFachadaImpl();
      resultado = fachadaFiltros.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear("Parametros filtrados obtenidos exitosamente", resultado));
    } else {
      ConsultarParametroTodosFachada fachadaTodos = new ConsultarParametroTodosFachadaImpl();
      resultado = fachadaTodos.ejecutar();
      return ResponseEntity.ok(
          RespuestaExito.crear("Parametros obtenidos exitosamente", resultado));
    }
  }

  @PostMapping
  public ResponseEntity<RespuestaExito<Void>> registrar(@RequestBody ParametroDTO dto) {
    RegistrarParametroFachada fachada = new RegistrarParametroFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.status(201)
        .body(RespuestaExito.crear("Parametro registrado exitosamente"));
  }

  @PutMapping
  public ResponseEntity<RespuestaExito<ParametroEntidad>> modificar(@RequestBody ParametroDTO dto) {
    ModificarParametroFachada fachada = new ModificarParametroFachadaImpl();
    ParametroEntidad resultado = new ParametroEntidad.Builder().build();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Parametro modificado exitosamente", resultado));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<RespuestaExito<Void>> eliminar(@PathVariable UUID id) {
    ParametroDTO dto = new ParametroDTO.Builder().id(id).build();
    EliminarParametroFachada fachada = new EliminarParametroFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Parametro eliminado exitosamente"));
  }
}
