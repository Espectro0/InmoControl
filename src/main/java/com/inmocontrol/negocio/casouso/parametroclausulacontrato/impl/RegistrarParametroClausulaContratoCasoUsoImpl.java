package com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.RegistrarParametroClausulaContratoCasoUso;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarParametroClausulaContratoCasoUsoImpl
    implements RegistrarParametroClausulaContratoCasoUso {

  private DAOFactory daoFactory;

  public RegistrarParametroClausulaContratoCasoUsoImpl(DAOFactory daoFactory) {
    super();
    this.daoFactory = daoFactory;
  }

  @Override
  public void ejecutar(ParametroClausulaContratoDominio datos) {
    validarObligatoriedadCampos(datos);
    registrarParametroClausulaContrato(datos);
  }

  private void validarObligatoriedadCampos(ParametroClausulaContratoDominio datos) {
    if (UtilObjeto.esNulo(datos)) {
      throw new ValidacionExcepcion("El parametro clausula contrato a registrar no es valido.");
    }
    if (UtilObjeto.esNulo(datos.getParametro())) {
      throw new ValidacionExcepcion("El parametro es obligatorio.");
    }
    if (UtilObjeto.esNulo(datos.getClausulaPorContrato())) {
      throw new ValidacionExcepcion("La clausula por contrato es obligatoria.");
    }
    if (UtilObjeto.esNulo(datos.getValor()) || datos.getValor().isEmpty()) {
      throw new ValidacionExcepcion("El valor del parametro clausula contrato es obligatorio.");
    }
  }

  private void registrarParametroClausulaContrato(ParametroClausulaContratoDominio datos) {
    var idUnico =
        UtilUUID.generarUnico(
            uuid -> daoFactory.obtenerParametroClausulaContratoDAO().consultarPorId(uuid) != null);
    ParametroClausulaContratoEntidad entidad =
        new ParametroClausulaContratoEntidad.Builder()
            .id(idUnico)
            .parametro(
                new com.inmocontrol.entidad.ParametroEntidad.Builder()
                    .id(datos.getParametro().getId())
                    .build())
            .clausulaPorContrato(
                new com.inmocontrol.entidad.ClausulaPorContratoEntidad.Builder()
                    .id(datos.getClausulaPorContrato().getId())
                    .build())
            .valor(datos.getValor())
            .build();
    daoFactory.obtenerParametroClausulaContratoDAO().crear(entidad);
  }
}
