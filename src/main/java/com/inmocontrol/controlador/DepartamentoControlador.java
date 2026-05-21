package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.DepartamentoDTO;
import com.inmocontrol.dto.PaisDTO;
import com.inmocontrol.entidad.DepartamentoEntidad;
import com.inmocontrol.negocio.fachada.departamento.ConsultarDepartamentoPorFiltrosFachada;
import com.inmocontrol.negocio.fachada.departamento.ConsultarDepartamentoPorIdFachada;
import com.inmocontrol.negocio.fachada.departamento.ConsultarDepartamentoTodosFachada;
import com.inmocontrol.negocio.fachada.departamento.impl.ConsultarDepartamentoPorFiltrosFachadaImpl;
import com.inmocontrol.negocio.fachada.departamento.impl.ConsultarDepartamentoPorIdFachadaImpl;
import com.inmocontrol.negocio.fachada.departamento.impl.ConsultarDepartamentoTodosFachadaImpl;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<DepartamentoEntidad>> consultarPorId(@PathVariable UUID id) {
    DepartamentoDTO dto = new DepartamentoDTO.Builder().id(id).build();
    ConsultarDepartamentoPorIdFachada fachada = new ConsultarDepartamentoPorIdFachadaImpl();
    DepartamentoEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Departamento obtenido exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<DepartamentoEntidad>>> consultar(
      @RequestParam(required = false) String nombre, @RequestParam(required = false) UUID paisId) {

    DepartamentoDTO dto =
        new DepartamentoDTO.Builder()
            .nombre(nombre)
            .pais(paisId != null ? new PaisDTO.Builder().id(paisId).build() : null)
            .build();

    List<DepartamentoEntidad> resultado;
    boolean tieneFiltros = nombre != null || paisId != null;

    if (tieneFiltros) {
      ConsultarDepartamentoPorFiltrosFachada fachadaFiltros =
          new ConsultarDepartamentoPorFiltrosFachadaImpl();
      resultado = fachadaFiltros.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear("Departamentos filtrados obtenidos exitosamente", resultado));
    } else {
      ConsultarDepartamentoTodosFachada fachadaTodos = new ConsultarDepartamentoTodosFachadaImpl();
      resultado = fachadaTodos.ejecutar();
      return ResponseEntity.ok(
          RespuestaExito.crear("Departamentos obtenidos exitosamente", resultado));
    }
  }
}
