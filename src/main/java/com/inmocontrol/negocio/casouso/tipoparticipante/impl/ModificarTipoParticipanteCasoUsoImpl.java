package com.inmocontrol.negocio.casouso.tipoparticipante.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoParticipanteEntidad;
import com.inmocontrol.negocio.casouso.tipoparticipante.ModificarTipoParticipanteCasoUso;
import com.inmocontrol.negocio.dominio.TipoParticipanteDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilValidacion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ModificarTipoParticipanteCasoUsoImpl implements ModificarTipoParticipanteCasoUso {

  private DAOFactory daoFactory;

  public ModificarTipoParticipanteCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(TipoParticipanteDominio datos) {
    validarObligatoriedadId(datos);
    validarExistenciaTipoParticipante(datos);
    validarFormatos(datos);
    validarUnicoNombre(datos);
    modificarTipoParticipante(datos);
  }

  private void validarObligatoriedadId(TipoParticipanteDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("El tipo de participante a modificar no es valido.");
    }
    if (UtilObjeto.esNulo(datos.getId())) {
      throw new ValidacionExcepcion("El ID del tipo de participante es obligatorio.");
    }
  }

  private void validarExistenciaTipoParticipante(TipoParticipanteDominio datos) {
    TipoParticipanteEntidad existente =
        daoFactory.obtenerTipoParticipanteDAO().consultarPorId(datos.getId());
    if (UtilObjeto.esNulo(existente)) {
      throw new ValidacionExcepcion(
          "No existe un tipo de participante con el ID: " + datos.getId());
    }
  }

  private void validarFormatos(TipoParticipanteDominio datos) {
    if (!UtilValidacion.validarLongitud(datos.getNombre(), 1, 50)) {
      throw new ValidacionExcepcion(
          "El nombre del tipo de participante debe tener entre 1 y 50 caracteres.");
    }
  }

  private void validarUnicoNombre(TipoParticipanteDominio datos) {
    TipoParticipanteEntidad filtro =
        new TipoParticipanteEntidad.Builder().nombre(datos.getNombre()).build();
    var resultados = daoFactory.obtenerTipoParticipanteDAO().consultarPorFiltro(filtro);
    for (TipoParticipanteEntidad item : resultados) {
      if (!item.getId().equals(datos.getId())) {
        throw new ValidacionExcepcion(
            "Ya existe un tipo de participante con el nombre: " + datos.getNombre());
      }
    }
  }

  private void modificarTipoParticipante(TipoParticipanteDominio datos) {
    TipoParticipanteEntidad entidad =
        new TipoParticipanteEntidad.Builder()
            .id(datos.getId())
            .nombre(UtilSanitizacion.sanitizar(datos.getNombre()))
            .build();
    daoFactory.obtenerTipoParticipanteDAO().actualizar(entidad.getId(), entidad);
  }
}
