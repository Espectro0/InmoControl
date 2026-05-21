package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.ContratoDTO;
import com.inmocontrol.dto.PropiedadDTO;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.fachada.contrato.*;
import com.inmocontrol.negocio.fachada.contrato.impl.*;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contratos")
public class ContratoControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<ContratoEntidad>> consultarPorId(@PathVariable UUID id) {
    ContratoDTO dto = new ContratoDTO.Builder().id(id).build();
    ConsultarContratoPorIdFachada fachada = new ConsultarContratoPorIdFachadaImpl();
    ContratoEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Contrato obtenido exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<ContratoEntidad>>> consultar(
      @RequestParam(required = false) String codigoContrato,
      @RequestParam(required = false) Boolean esActivo,
      @RequestParam(required = false) UUID propiedadId,
      @RequestParam(required = false) String fechaInicio,
      @RequestParam(required = false) String fechaFin) {

    ContratoDTO dto =
        new ContratoDTO.Builder()
            .codigoContrato(codigoContrato)
            .esActivo(esActivo)
            .propiedad(
                propiedadId != null ? new PropiedadDTO.Builder().id(propiedadId).build() : null)
            .build();

    List<ContratoEntidad> resultado;
    boolean tieneFiltros =
        codigoContrato != null
            || esActivo != null
            || propiedadId != null
            || fechaInicio != null
            || fechaFin != null;

    if (tieneFiltros) {
      ConsultarContratoPorFiltrosFachada fachadaFiltros =
          new ConsultarContratoPorFiltrosFachadaImpl();
      resultado = fachadaFiltros.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear("Contratos filtrados obtenidos exitosamente", resultado));
    } else {
      ConsultarContratoTodosFachada fachadaTodos = new ConsultarContratoTodosFachadaImpl();
      resultado = fachadaTodos.ejecutar();
      return ResponseEntity.ok(RespuestaExito.crear("Contratos obtenidos exitosamente", resultado));
    }
  }

  @PostMapping
  public ResponseEntity<RespuestaExito<ContratoEntidad>> registrar(@RequestBody ContratoDTO dto) {
    RegistrarContratoFachada fachada = new RegistrarContratoFachadaImpl();
    ContratoEntidad resultado = new ContratoEntidad.Builder().build();
    fachada.ejecutar(dto);
    return ResponseEntity.status(201)
        .body(RespuestaExito.crear("Contrato registrado exitosamente", resultado));
  }

  @PutMapping
  public ResponseEntity<RespuestaExito<ContratoEntidad>> modificar(@RequestBody ContratoDTO dto) {
    ModificarContratoFachada fachada = new ModificarContratoFachadaImpl();
    ContratoEntidad resultado = new ContratoEntidad.Builder().build();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Contrato modificado exitosamente", resultado));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<RespuestaExito<Void>> eliminar(@PathVariable UUID id) {
    ContratoDTO dto = new ContratoDTO.Builder().id(id).build();
    EliminarContratoFachada fachada = new EliminarContratoFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Contrato eliminado exitosamente", null));
  }

  @PostMapping("/suspender")
  public ResponseEntity<RespuestaExito<Void>> suspender(@RequestBody ContratoDTO dto) {
    SuspenderContratoFachada fachada = new SuspenderContratoFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Contrato suspendido exitosamente", null));
  }

  @PostMapping("/activar")
  public ResponseEntity<RespuestaExito<Void>> activar(@RequestBody ContratoDTO dto) {
    ActivarContratoFachada fachada = new ActivarContratoFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Contrato activado exitosamente", null));
  }
}
