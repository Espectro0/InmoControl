package com.inmocontrol.negocio.casouso.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulacontrato.EliminarClausulaContratoCasoUso;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarClausulaContratoCasoUsoImpl implements EliminarClausulaContratoCasoUso {

	private DAOFactory daoFactory;

	public EliminarClausulaContratoCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ClausulaContratoDominio datos) {
		validarObligatoriedadId(datos);
		validarExistenciaClausulaContrato(datos);
		eliminarClausulaContrato(datos);
	}

	private void validarObligatoriedadId(ClausulaContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("La clausula contrato a eliminar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID de la clausula contrato es obligatorio.");
		}
	}

	private void validarExistenciaClausulaContrato(ClausulaContratoDominio datos) {
		ClausulaContratoEntidad existente = daoFactory.obtenerClausulaContratoDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(existente)) {
			throw new ValidacionExcepcion("No existe una clausula contrato con el ID: " + datos.getId());
		}
	}

	private void eliminarClausulaContrato(ClausulaContratoDominio datos) {
		daoFactory.obtenerClausulaContratoDAO().eliminar(datos.getId());
	}
}