package com.inmocontrol.negocio.casouso.parametro.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroEntidad;
import com.inmocontrol.negocio.casouso.parametro.RegistrarParametroCasoUso;
import com.inmocontrol.negocio.dominio.ParametroDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.UtilValidacion;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarParametroCasoUsoImpl implements RegistrarParametroCasoUso {

  private DAOFactory daoFactory;

  public RegistrarParametroCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ParametroDominio datos) {
    validarObligatoriedadCampos(datos);
    validarFormatos(datos);
    validarUnicoPlaceholder(datos);
    registrarParametro(datos);
  }

  private void validarObligatoriedadCampos(ParametroDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("El parametro a registrar no es valido.");
    }
    if (UtilObjeto.esNulo(datos.getPlaceholder()) || datos.getPlaceholder().isEmpty()) {
      throw new ValidacionExcepcion("El placeholder del parametro es obligatorio.");
    }
    if (UtilObjeto.esNulo(datos.getDescripcion()) || datos.getDescripcion().isEmpty()) {
      throw new ValidacionExcepcion("La descripcion del parametro es obligatoria.");
    }
  }

  private void validarFormatos(ParametroDominio datos) {
    if (!UtilValidacion.validarLongitud(datos.getPlaceholder(), 1, 30)) {
      throw new ValidacionExcepcion("El placeholder debe tener entre 1 y 30 caracteres.");
    }
    if (!UtilValidacion.validarLongitud(datos.getDescripcion(), 1, 100)) {
      throw new ValidacionExcepcion("La descripcion debe tener entre 1 y 100 caracteres.");
    }
  }

  private void validarUnicoPlaceholder(ParametroDominio datos) {
    ParametroEntidad filtro =
        new ParametroEntidad.Builder().placeholder(datos.getPlaceholder()).build();
    var resultados = daoFactory.obtenerParametroDAO().consultarPorFiltro(filtro);
    if (!resultados.isEmpty()) {
      throw new ValidacionExcepcion(
          "Ya existe un parametro con el placeholder: " + datos.getPlaceholder());
    }
  }

  private void registrarParametro(ParametroDominio datos) {
    var idUnico =
        UtilUUID.generarUnico(
            uuid -> daoFactory.obtenerParametroDAO().consultarPorId(uuid) != null);
    ParametroEntidad entidad =
        new ParametroEntidad.Builder()
            .id(idUnico)
            .placeholder(UtilSanitizacion.sanitizar(datos.getPlaceholder()))
            .descripcion(UtilSanitizacion.sanitizar(datos.getDescripcion()))
            .build();
    daoFactory.obtenerParametroDAO().crear(entidad);
  }
}
