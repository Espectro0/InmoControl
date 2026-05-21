package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.CiudadDTO;
import com.inmocontrol.dto.EstratoDTO;
import com.inmocontrol.dto.PropiedadDTO;
import com.inmocontrol.dto.TipoPropiedadDTO;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.fachada.propiedad.*;
import com.inmocontrol.negocio.fachada.propiedad.impl.*;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/propiedades")
public class PropiedadControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<PropiedadEntidad>> consultarPorId(@PathVariable UUID id) {
    PropiedadDTO dto = new PropiedadDTO.Builder().id(id).build();
    ConsultarPropiedadPorIdFachada fachada = new ConsultarPropiedadPorIdFachadaImpl();
    PropiedadEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Propiedad obtenida exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<PropiedadEntidad>>> consultar(
      @RequestParam(required = false) String nombreInmueble,
      @RequestParam(required = false) String direccion,
      @RequestParam(required = false) UUID tipoPropiedadId,
      @RequestParam(required = false) UUID estratoId,
      @RequestParam(required = false) UUID ciudadId,
      @RequestParam(required = false) Integer areaMetros) {

    PropiedadDTO dto =
        new PropiedadDTO.Builder()
            .nombreInmueble(nombreInmueble)
            .direccion(direccion)
            .tipoPropiedad(
                tipoPropiedadId != null
                    ? new TipoPropiedadDTO.Builder().id(tipoPropiedadId).build()
                    : null)
            .estrato(estratoId != null ? new EstratoDTO.Builder().id(estratoId).build() : null)
            .ciudad(ciudadId != null ? new CiudadDTO.Builder().id(ciudadId).build() : null)
            .areaMetros(areaMetros)
            .build();

    List<PropiedadEntidad> resultado;
    boolean tieneFiltros =
        nombreInmueble != null
            || direccion != null
            || tipoPropiedadId != null
            || estratoId != null
            || ciudadId != null
            || areaMetros != null;

    if (tieneFiltros) {
      ConsultarPropiedadPorFiltrosFachada fachadaFiltros =
          new ConsultarPropiedadPorFiltrosFachadaImpl();
      resultado = fachadaFiltros.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear("Propiedades filtradas obtenidas exitosamente", resultado));
    } else {
      ConsultarPropiedadTodosFachada fachadaTodos = new ConsultarPropiedadTodosFachadaImpl();
      resultado = fachadaTodos.ejecutar();
      return ResponseEntity.ok(
          RespuestaExito.crear("Propiedades obtenidas exitosamente", resultado));
    }
  }

  @PostMapping
  public ResponseEntity<RespuestaExito<PropiedadEntidad>> registrar(@RequestBody PropiedadDTO dto) {
    RegistrarPropiedadFachada fachada = new RegistrarPropiedadFachadaImpl();
    PropiedadEntidad resultado = new PropiedadEntidad.Builder().build();
    fachada.ejecutar(dto);
    return ResponseEntity.status(201)
        .body(RespuestaExito.crear("Propiedad registrada exitosamente", resultado));
  }

  @PutMapping
  public ResponseEntity<RespuestaExito<PropiedadEntidad>> modificar(@RequestBody PropiedadDTO dto) {
    ModificarPropiedadFachada fachada = new ModificarPropiedadFachadaImpl();
    PropiedadEntidad resultado = new PropiedadEntidad.Builder().build();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Propiedad modificada exitosamente", resultado));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<RespuestaExito<Void>> eliminar(@PathVariable UUID id) {
    PropiedadDTO dto = new PropiedadDTO.Builder().id(id).build();
    EliminarPropiedadFachada fachada = new EliminarPropiedadFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Propiedad eliminada exitosamente", null));
  }
}
