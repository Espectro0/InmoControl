package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.TipoPropiedadDTO;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.fachada.tipopropiedad.ConsultarTipoPropiedadPorFiltrosFachada;
import com.inmocontrol.negocio.fachada.tipopropiedad.ConsultarTipoPropiedadPorIdFachada;
import com.inmocontrol.negocio.fachada.tipopropiedad.ConsultarTipoPropiedadTodosFachada;
import com.inmocontrol.negocio.fachada.tipopropiedad.impl.ConsultarTipoPropiedadPorFiltrosFachadaImpl;
import com.inmocontrol.negocio.fachada.tipopropiedad.impl.ConsultarTipoPropiedadPorIdFachadaImpl;
import com.inmocontrol.negocio.fachada.tipopropiedad.impl.ConsultarTipoPropiedadTodosFachadaImpl;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tipos-propiedad")
public class TipoPropiedadControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<TipoPropiedadEntidad>> consultarPorId(
      @PathVariable UUID id) {
    TipoPropiedadDTO dto = new TipoPropiedadDTO.Builder().id(id).build();
    ConsultarTipoPropiedadPorIdFachada fachada = new ConsultarTipoPropiedadPorIdFachadaImpl();
    TipoPropiedadEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Tipo de propiedad obtenido exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<TipoPropiedadEntidad>>> consultar(
      @RequestParam(required = false) String nombre) {

    TipoPropiedadDTO dto = new TipoPropiedadDTO.Builder().nombre(nombre).build();

    List<TipoPropiedadEntidad> resultado;
    boolean tieneFiltros = nombre != null;

    if (tieneFiltros) {
      ConsultarTipoPropiedadPorFiltrosFachada fachadaFiltros =
          new ConsultarTipoPropiedadPorFiltrosFachadaImpl();
      resultado = fachadaFiltros.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear("Tipos de propiedad filtrados obtenidos exitosamente", resultado));
    } else {
      ConsultarTipoPropiedadTodosFachada fachadaTodos =
          new ConsultarTipoPropiedadTodosFachadaImpl();
      resultado = fachadaTodos.ejecutar();
      return ResponseEntity.ok(
          RespuestaExito.crear("Tipos de propiedad obtenidos exitosamente", resultado));
    }
  }
}
