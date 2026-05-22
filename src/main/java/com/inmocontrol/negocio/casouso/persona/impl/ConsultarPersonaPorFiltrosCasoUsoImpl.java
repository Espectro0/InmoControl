package com.inmocontrol.negocio.casouso.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.CiudadEntidad;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.casouso.persona.ConsultarPersonaPorFiltrosCasoUso;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.transversal.UtilObjeto;
import java.util.List;

public class ConsultarPersonaPorFiltrosCasoUsoImpl implements ConsultarPersonaPorFiltrosCasoUso {

  private DAOFactory daoFactory;

  public ConsultarPersonaPorFiltrosCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public List<PersonaEntidad> ejecutar(PersonaDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      return daoFactory.obtenerPersonaDAO().consultarTodos();
    }
    return daoFactory
        .obtenerPersonaDAO()
        .consultarPorFiltro(
            new PersonaEntidad.Builder()
                .numeroIdentificacion(datos.getNumeroIdentificacion())
                .primerNombre(datos.getPrimerNombre())
                .primerApellido(datos.getPrimerApellido())
                .correoElectronico(datos.getCorreoElectronico())
                .tipoDocumento(datos.getTipoDocumento() != null
                    ? new TipoDocumentoEntidad.Builder().id(datos.getTipoDocumento().getId()).build()
                    : null)
                .ciudadResidencia(datos.getCiudadResidencia() != null
                    ? new CiudadEntidad.Builder().id(datos.getCiudadResidencia().getId()).build()
                    : null)
                .build());
  }
}
