package com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.ModificarParametroClausulaContratoCasoUso;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ModificarParametroClausulaContratoCasoUsoImpl implements ModificarParametroClausulaContratoCasoUso {

	private DAOFactory daoFactory;

	public ModificarParametroClausulaContratoCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ParametroClausulaContratoDominio datos) {
		validarObligatoriedadId(datos);
		validarExistenciaParametroClausulaContrato(datos);
		modificarParametroClausulaContrato(datos);
	}

	private void validarObligatoriedadId(ParametroClausulaContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("El parametro clausula contrato a modificar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID del parametro clausula contrato es obligatorio.");
		}
	}

	private void validarExistenciaParametroClausulaContrato(ParametroClausulaContratoDominio datos) {
		ParametroClausulaContratoEntidad existente = daoFactory.obtenerParametroClausulaContratoDAO()
				.consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(existente)) {
			throw new ValidacionExcepcion("No existe un parametro clausula contrato con el ID: " + datos.getId());
		}
	}

	private void modificarParametroClausulaContrato(ParametroClausulaContratoDominio datos) {
		ParametroClausulaContratoEntidad entidad = new ParametroClausulaContratoEntidad.Builder().id(datos.getId())
				.parametro(new com.inmocontrol.entidad.ParametroEntidad.Builder()
						.id(datos.getParametro() != null ? datos.getParametro().getId() : null).build())
				.clausulaPorContrato(new com.inmocontrol.entidad.ClausulaPorContratoEntidad.Builder()
						.id(datos.getClausulaPorContrato() != null ? datos.getClausulaPorContrato().getId() : null)
						.build())
				.valor(datos.getValor()).build();
		daoFactory.obtenerParametroClausulaContratoDAO().actualizar(entidad.getId(), entidad);
	}
}