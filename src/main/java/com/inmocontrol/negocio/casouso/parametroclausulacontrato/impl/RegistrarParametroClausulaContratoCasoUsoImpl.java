package com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.RegistrarParametroClausulaContratoCasoUso;
import com.inmocontrol.transversal.excepcion.ValidadorExcepcion;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilUUID;

public class RegistrarParametroClausulaContratoCasoUsoImpl implements RegistrarParametroClausulaContratoCasoUso {

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
			throw new InmocontrolExcepcion("El parametro clausula contrato a registrar no es valido.",
					"Validacion fallida en RegistrarParametroClausulaContratoCasoUsoImpl.validarObligatoriedadCampos() - El parametro clausula contrato a registrar no es valido.");
		}
		if (datos.getParametro().getId().equals(UtilUUID.UUID_CERO)) {
			throw new ValidadorExcepcion("El parámetro es obligatorio");
		}
		if (datos.getClausulaPorContrato().getId().equals(UtilUUID.UUID_CERO)) {
			throw new ValidadorExcepcion("La cláusula por contrato es obligatoria");
		}
		if (UtilObjeto.esNulo(datos.getValor()) || datos.getValor().isEmpty()) {
			throw new InmocontrolExcepcion("El valor del parametro clausula contrato es obligatorio.",
					"Validacion fallida en RegistrarParametroClausulaContratoCasoUsoImpl.validarObligatoriedadCampos() - El valor del parametro clausula contrato es obligatorio.");
		}
	}

	private void registrarParametroClausulaContrato(ParametroClausulaContratoDominio datos) {
		var idUnico = UtilUUID
				.generarUnico(uuid -> daoFactory.obtenerParametroClausulaContratoDAO().consultarPorId(uuid) != null);
		ParametroClausulaContratoEntidad entidad = new ParametroClausulaContratoEntidad.Builder().id(idUnico)
				.parametro(
						new com.inmocontrol.entidad.ParametroEntidad.Builder().id(datos.getParametro().getId()).build())
				.clausulaPorContrato(new com.inmocontrol.entidad.ClausulaPorContratoEntidad.Builder()
						.id(datos.getClausulaPorContrato().getId()).build())
				.valor(datos.getValor()).build();
		daoFactory.obtenerParametroClausulaContratoDAO().crear(entidad);
	}
}
