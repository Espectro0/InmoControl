package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.EstratoDTO;
import com.inmocontrol.entidad.EstratoEntidad;
import com.inmocontrol.negocio.fachada.estrato.ConsultarEstratoPorFiltrosFachada;
import com.inmocontrol.negocio.fachada.estrato.ConsultarEstratoPorIdFachada;
import com.inmocontrol.negocio.fachada.estrato.ConsultarEstratoTodosFachada;
import com.inmocontrol.negocio.fachada.estrato.impl.ConsultarEstratoPorFiltrosFachadaImpl;
import com.inmocontrol.negocio.fachada.estrato.impl.ConsultarEstratoPorIdFachadaImpl;
import com.inmocontrol.negocio.fachada.estrato.impl.ConsultarEstratoTodosFachadaImpl;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/estratos")
public class EstratoControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<EstratoEntidad>> consultarPorId(@PathVariable UUID id) {
    EstratoDTO dto = new EstratoDTO.Builder().id(id).build();
    ConsultarEstratoPorIdFachada fachada = new ConsultarEstratoPorIdFachadaImpl();
    EstratoEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Estrato obtenido exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<EstratoEntidad>>> consultar(
      @RequestParam(required = false) String nombre,
      @RequestParam(required = false) String descripcion) {

    EstratoDTO dto = new EstratoDTO.Builder().nombre(nombre).descripcion(descripcion).build();

    List<EstratoEntidad> resultado;
    boolean tieneFiltros = nombre != null || descripcion != null;

    if (tieneFiltros) {
      ConsultarEstratoPorFiltrosFachada fachadaFiltros =
          new ConsultarEstratoPorFiltrosFachadaImpl();
      resultado = fachadaFiltros.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear("Estratos filtrados obtenidos exitosamente", resultado));
    } else {
      ConsultarEstratoTodosFachada fachadaTodos = new ConsultarEstratoTodosFachadaImpl();
      resultado = fachadaTodos.ejecutar();
      return ResponseEntity.ok(RespuestaExito.crear("Estratos obtenidos exitosamente", resultado));
    }
  }
}
