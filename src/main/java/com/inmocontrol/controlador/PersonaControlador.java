package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.CiudadDTO;
import com.inmocontrol.dto.PersonaDTO;
import com.inmocontrol.dto.TipoDocumentoDTO;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.negocio.fachada.persona.*;
import com.inmocontrol.negocio.fachada.persona.impl.*;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/personas")
public class PersonaControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<PersonaEntidad>> consultarPorId(@PathVariable UUID id) {
    PersonaDTO dto = new PersonaDTO.Builder().id(id).build();
    ConsultarPersonaPorIdFachada fachada = new ConsultarPersonaPorIdFachadaImpl();
    PersonaEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Persona obtenida exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<PersonaEntidad>>> consultar(
      @RequestParam(required = false) String numeroIdentificacion,
      @RequestParam(required = false) String primerNombre,
      @RequestParam(required = false) String primerApellido,
      @RequestParam(required = false) String correoElectronico,
      @RequestParam(required = false) UUID tipoDocumentoId,
      @RequestParam(required = false) UUID ciudadResidenciaId) {

    PersonaDTO dto =
        new PersonaDTO.Builder()
            .numeroIdentificacion(numeroIdentificacion)
            .primerNombre(primerNombre)
            .primerApellido(primerApellido)
            .correoElectronico(correoElectronico)
            .tipoDocumento(
                tipoDocumentoId != null
                    ? new TipoDocumentoDTO.Builder().id(tipoDocumentoId).build()
                    : null)
            .ciudadResidencia(
                ciudadResidenciaId != null
                    ? new CiudadDTO.Builder().id(ciudadResidenciaId).build()
                    : null)
            .build();

    List<PersonaEntidad> resultado;
    boolean tieneFiltros =
        numeroIdentificacion != null
            || primerNombre != null
            || primerApellido != null
            || correoElectronico != null
            || tipoDocumentoId != null
            || ciudadResidenciaId != null;

    if (tieneFiltros) {
      ConsultarPersonaPorFiltrosFachada fachadaFiltros =
          new ConsultarPersonaPorFiltrosFachadaImpl();
      resultado = fachadaFiltros.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear("Personas filtradas obtenidas exitosamente", resultado));
    } else {
      ConsultarPersonaTodosFachada fachadaTodos = new ConsultarPersonaTodosFachadaImpl();
      resultado = fachadaTodos.ejecutar();
      return ResponseEntity.ok(RespuestaExito.crear("Personas obtenidas exitosamente", resultado));
    }
  }

  @PostMapping
  public ResponseEntity<RespuestaExito<Void>> registrar(@RequestBody PersonaDTO dto) {
    RegistrarPersonaFachada fachada = new RegistrarPersonaFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.status(201)
        .body(RespuestaExito.crear("Persona registrada exitosamente"));
  }

  @PutMapping
  public ResponseEntity<RespuestaExito<PersonaEntidad>> modificar(@RequestBody PersonaDTO dto) {
    ModificarPersonaFachada fachada = new ModificarPersonaFachadaImpl();
    PersonaEntidad resultado = new PersonaEntidad.Builder().build();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Persona modificada exitosamente", resultado));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<RespuestaExito<Void>> eliminar(@PathVariable UUID id) {
    PersonaDTO dto = new PersonaDTO.Builder().id(id).build();
    EliminarPersonaFachada fachada = new EliminarPersonaFachadaImpl();
    fachada.ejecutar(dto);
    return ResponseEntity.ok(RespuestaExito.crear("Persona eliminada exitosamente"));
  }
}
