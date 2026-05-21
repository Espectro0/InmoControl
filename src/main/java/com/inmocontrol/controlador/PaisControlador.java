package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.PaisDTO;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.negocio.fachada.pais.*;
import com.inmocontrol.negocio.fachada.pais.impl.*;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paises")
public class PaisControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<PaisEntidad>> consultarPorId(@PathVariable UUID id) {
    PaisDTO dto = new PaisDTO.Builder().id(id).build();
    ConsultarPaisPorIdFachada fachada = new ConsultarPaisPorIdFachadaImpl();
    PaisEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Pais obtenido exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<PaisEntidad>>> consultar(
      @RequestParam(required = false) String nombre) {
    PaisDTO dto = new PaisDTO.Builder().nombre(nombre).build();
    boolean tieneFiltros = nombre != null && !nombre.isEmpty();

    if (tieneFiltros) {
      ConsultarPaisPorFiltrosFachada fachada = new ConsultarPaisPorFiltrosFachadaImpl();
      List<PaisEntidad> resultado = fachada.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear("Paises filtrados obtenidos exitosamente", resultado));
    } else {
      ConsultarPaisTodosFachada fachada = new ConsultarPaisTodosFachadaImpl();
      List<PaisEntidad> resultado = fachada.ejecutar();
      return ResponseEntity.ok(RespuestaExito.crear("Paises obtenidos exitosamente", resultado));
    }
  }
}
