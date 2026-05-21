package com.inmocontrol.controlador;

import com.inmocontrol.controlador.respuesta.RespuestaExito;
import com.inmocontrol.dto.TipoDocumentoDTO;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.fachada.tipodocumento.ConsultarTipoDocumentoPorFiltrosFachada;
import com.inmocontrol.negocio.fachada.tipodocumento.ConsultarTipoDocumentoPorIdFachada;
import com.inmocontrol.negocio.fachada.tipodocumento.ConsultarTipoDocumentoTodosFachada;
import com.inmocontrol.negocio.fachada.tipodocumento.impl.ConsultarTipoDocumentoPorFiltrosFachadaImpl;
import com.inmocontrol.negocio.fachada.tipodocumento.impl.ConsultarTipoDocumentoPorIdFachadaImpl;
import com.inmocontrol.negocio.fachada.tipodocumento.impl.ConsultarTipoDocumentoTodosFachadaImpl;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tipos-documento")
public class TipoDocumentoControlador {

  @GetMapping("/{id}")
  public ResponseEntity<RespuestaExito<TipoDocumentoEntidad>> consultarPorId(
      @PathVariable UUID id) {
    TipoDocumentoDTO dto = new TipoDocumentoDTO.Builder().id(id).build();
    ConsultarTipoDocumentoPorIdFachada fachada = new ConsultarTipoDocumentoPorIdFachadaImpl();
    TipoDocumentoEntidad resultado = fachada.ejecutar(dto);
    return ResponseEntity.ok(
        RespuestaExito.crear("Tipo de documento obtenido exitosamente", resultado));
  }

  @GetMapping
  public ResponseEntity<RespuestaExito<List<TipoDocumentoEntidad>>> consultar(
      @RequestParam(required = false) String nombre) {

    TipoDocumentoDTO dto = new TipoDocumentoDTO.Builder().nombre(nombre).build();

    List<TipoDocumentoEntidad> resultado;
    boolean tieneFiltros = nombre != null;

    if (tieneFiltros) {
      ConsultarTipoDocumentoPorFiltrosFachada fachadaFiltros =
          new ConsultarTipoDocumentoPorFiltrosFachadaImpl();
      resultado = fachadaFiltros.ejecutar(dto);
      return ResponseEntity.ok(
          RespuestaExito.crear("Tipos de documento filtrados obtenidos exitosamente", resultado));
    } else {
      ConsultarTipoDocumentoTodosFachada fachadaTodos =
          new ConsultarTipoDocumentoTodosFachadaImpl();
      resultado = fachadaTodos.ejecutar();
      return ResponseEntity.ok(
          RespuestaExito.crear("Tipos de documento obtenidos exitosamente", resultado));
    }
  }
}
