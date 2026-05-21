package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.CiudadDTO;
import com.inmocontrol.dto.DepartamentoDTO;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.negocio.fachada.ciudad.ConsultarCiudadPorFiltrosFachada;
import com.inmocontrol.negocio.fachada.ciudad.ConsultarCiudadPorIdFachada;
import com.inmocontrol.negocio.fachada.ciudad.ConsultarCiudadTodosFachada;
import com.inmocontrol.negocio.fachada.ciudad.impl.ConsultarCiudadPorFiltrosFachadaImpl;
import com.inmocontrol.negocio.fachada.ciudad.impl.ConsultarCiudadPorIdFachadaImpl;
import com.inmocontrol.negocio.fachada.ciudad.impl.ConsultarCiudadTodosFachadaImpl;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<CiudadEntidad>> consultarPorId(@PathVariable UUID id) {
    CiudadDTO dto = new CiudadDTO.Builder().id(id).build();
    ConsultarCiudadPorIdFachada fachada = new ConsultarCiudadPorIdFachadaImpl();
    CiudadEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Ciudad obtenida exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<CiudadEntidad>>> consultar(
      @RequestParam(required = false) String nombre,
      @RequestParam(required = false) UUID departamentoId) {

    CiudadDTO dto =
        new CiudadDTO.Builder()
            .nombre(nombre)
            .departamento(
                departamentoId != null
                    ? new DepartamentoDTO.Builder().id(departamentoId).build()
                    : null)
            .build();

    List<CiudadEntidad> resultado;
    boolean tieneFiltros = nombre != null || departamentoId != null;

    if (tieneFiltros) {
      ConsultarCiudadPorFiltrosFachada fachadaFiltros = new ConsultarCiudadPorFiltrosFachadaImpl();
      resultado = fachadaFiltros.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear("Ciudades filtradas obtenidas exitosamente", resultado));
    } else {
      ConsultarCiudadTodosFachada fachadaTodos = new ConsultarCiudadTodosFachadaImpl();
      resultado = fachadaTodos.ejecutar();
      return ResponseEntity.ok(RespuestaExito.crear("Ciudades obtenidas exitosamente", resultado));
    }
  }
}
