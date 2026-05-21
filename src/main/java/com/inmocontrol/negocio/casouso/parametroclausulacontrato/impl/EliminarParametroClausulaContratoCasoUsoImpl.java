package com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.EliminarParametroClausulaContratoCasoUso;
import com.inmocontrol.negocio.dominio.ParametroClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarParametroClausulaContratoCasoUsoImpl implements EliminarParametroClausulaContratoCasoUso {

	private DAOFactory daoFactory;

	public EliminarParametroClausulaContratoCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ParametroClausulaContratoDominio datos) {
		validarObligatoriedadId(datos);
		validarExistenciaParametroClausulaContrato(datos);
		eliminarParametroClausulaContrato(datos);
	}

	private void validarObligatoriedadId(ParametroClausulaContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("El parametro clausula contrato a eliminar no es valido.");
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

	private void eliminarParametroClausulaContrato(ParametroClausulaContratoDominio datos) {
		daoFactory.obtenerParametroClausulaContratoDAO().eliminar(datos.getId());
	}
}