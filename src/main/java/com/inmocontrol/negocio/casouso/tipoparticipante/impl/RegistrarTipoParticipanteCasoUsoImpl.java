package com.inmocontrol.negocio.casouso.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.tipoparticipante.RegistrarTipoParticipanteCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.UtilValidacion;


public class RegistrarTipoParticipanteCasoUsoImpl implements RegistrarTipoParticipanteCasoUso {

  private DAOFactory daoFactory;

  public RegistrarTipoParticipanteCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(TipoParticipanteDominio datos) {
    validarObligatoriedadCampos(datos);
    validarFormatos(datos);
    validarUnicoNombre(datos);
    registrarTipoParticipante(datos);
  }

  private void validarObligatoriedadCampos(TipoParticipanteDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new InmocontrolExcepcion(
          "El tipo de participante a registrar no es valido.",
          "Validacion fallida en RegistrarTipoParticipanteCasoUsoImpl.validarObligatoriedadCampos() - El tipo de participante a registrar no es valido."
      );
    }
    if (UtilObjeto.esNulo(datos.getNombre()) || datos.getNombre().isEmpty()) {
      throw new InmocontrolExcepcion(
          "El nombre del tipo de participante es obligatorio.",
          "Validacion fallida en RegistrarTipoParticipanteCasoUsoImpl.validarObligatoriedadCampos() - El nombre del tipo de participante es obligatorio."
      );
    }
  }

  private void validarFormatos(TipoParticipanteDominio datos) {
    if (!UtilValidacion.validarLongitud(datos.getNombre(), 1, 50)) {
      throw new InmocontrolExcepcion(
          "El nombre del tipo de participante debe tener entre 1 y 50 caracteres.",
          "Validacion fallida en RegistrarTipoParticipanteCasoUsoImpl - nombre muy largo"
      );
    }
  }

  private void validarUnicoNombre(TipoParticipanteDominio datos) {
    TipoParticipanteEntidad existente =
        new TipoParticipanteEntidad.Builder().nombre(datos.getNombre()).build();
    var resultados = daoFactory.obtenerTipoParticipanteDAO().consultarPorFiltro(existente);
    if (!resultados.isEmpty()) {
      throw new InmocontrolExcepcion(
          "Ya existe un tipo de participante con el nombre: " + datos.getNombre(),
          "Validacion fallida en RegistrarTipoParticipanteCasoUsoImpl - nombre duplicado"
      );
    }
  }

  private void registrarTipoParticipante(TipoParticipanteDominio datos) {
    var idUnico =
        UtilUUID.generarUnico(
            uuid -> daoFactory.obtenerTipoParticipanteDAO().consultarPorId(uuid) != null);
    TipoParticipanteEntidad entidad =
        new TipoParticipanteEntidad.Builder()
            .id(idUnico)
            .nombre(UtilSanitizacion.sanitizar(datos.getNombre()))
            .build();
    daoFactory.obtenerTipoParticipanteDAO().crear(entidad);
  }
}


