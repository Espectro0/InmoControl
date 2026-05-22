package com.inmocontrol.negocio.casouso.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.contrato.RegistrarContratoCasoUso;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.UtilValidacion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarContratoCasoUsoImpl implements RegistrarContratoCasoUso {

  private DAOFactory daoFactory;

  public RegistrarContratoCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ContratoDominio datos) {
    validarObligatoriedadCampos(datos);
    validarFormatos(datos);
    validarFechas(datos);
    validarUnicoCodigoContrato(datos);
    registrarContrato(datos);
  }

  private void validarObligatoriedadCampos(ContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("El contrato a registrar no es valido.");
    }
    if (UtilObjeto.esNulo(datos.getCodigoContrato()) || datos.getCodigoContrato().isEmpty()) {
      throw new ValidacionExcepcion("El codigo del contrato es obligatorio.");
    }
    if (UtilObjeto.esNulo(datos.getFechaInicio())) {
      throw new ValidacionExcepcion("La fecha de inicio es obligatoria.");
    }
    if (UtilObjeto.esNulo(datos.getPropiedad())
        || UtilObjeto.esNulo(datos.getPropiedad().getId())) {
      throw new ValidacionExcepcion("La propiedad es obligatoria.");
    }
  }

  private void validarFormatos(ContratoDominio datos) {
    if (!UtilValidacion.validarLongitud(datos.getCodigoContrato(), 1, 15)) {
      throw new ValidacionExcepcion("El codigo del contrato debe tener entre 1 y 15 caracteres.");
    }
  }

  private void validarFechas(ContratoDominio datos) {
    if (datos.getFechaFin() != null && datos.getFechaInicio().compareTo(datos.getFechaFin()) >= 0) {
      throw new ValidacionExcepcion(
          "La fecha de fin debe ser estrictamente mayor a la fecha de inicio.");
    }
  }

  private void validarUnicoCodigoContrato(ContratoDominio datos) {
    ContratoEntidad filtro =
        new ContratoEntidad.Builder().codigoContrato(datos.getCodigoContrato()).build();
    var resultados = daoFactory.obtenerContratoDAO().consultarPorFiltro(filtro);
    if (!resultados.isEmpty()) {
      throw new ValidacionExcepcion(
          "Ya existe un contrato con el codigo: " + datos.getCodigoContrato());
    }
  }

  private void registrarContrato(ContratoDominio datos) {
    var idUnico =
        UtilUUID.generarUnico(
            uuid -> daoFactory.obtenerContratoDAO().consultarPorId(uuid) != null);
    ContratoEntidad entidad =
        new ContratoEntidad.Builder()
            .id(idUnico)
            .codigoContrato(UtilSanitizacion.sanitizar(datos.getCodigoContrato()))
            .fechaInicio(datos.getFechaInicio())
            .fechaFin(datos.getFechaFin())
            .esActivo(datos.getEsActivo() != null ? datos.getEsActivo() : false)
            .propiedad(new PropiedadEntidad.Builder().id(datos.getPropiedad().getId()).build())
            .build();
    daoFactory.obtenerContratoDAO().crear(entidad);
  }
}
