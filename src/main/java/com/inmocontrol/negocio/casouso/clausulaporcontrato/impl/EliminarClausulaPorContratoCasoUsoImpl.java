package com.inmocontrol.negocio.casouso.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.EliminarClausulaPorContratoCasoUso;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarClausulaPorContratoCasoUsoImpl implements EliminarClausulaPorContratoCasoUso {

	private DAOFactory daoFactory;

	public EliminarClausulaPorContratoCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ClausulaPorContratoDominio datos) {
		validarObligatoriedadId(datos);
		validarExistenciaClausulaPorContrato(datos);
		eliminarClausulaPorContrato(datos);
	}

	private void validarObligatoriedadId(ClausulaPorContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("La clausula por contrato a eliminar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID de la clausula por contrato es obligatorio.");
		}
	}

	private void validarExistenciaClausulaPorContrato(ClausulaPorContratoDominio datos) {
		ClausulaPorContratoEntidad existente = daoFactory.obtenerClausulaPorContratoDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(existente)) {
			throw new ValidacionExcepcion("No existe una clausula por contrato con el ID: " + datos.getId());
		}
	}

	private void eliminarClausulaPorContrato(ClausulaPorContratoDominio datos) {
		daoFactory.obtenerClausulaPorContratoDAO().eliminar(datos.getId());
	}
}