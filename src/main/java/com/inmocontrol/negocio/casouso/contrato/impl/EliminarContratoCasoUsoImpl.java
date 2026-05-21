package com.inmocontrol.negocio.casouso.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.negocio.casouso.contrato.EliminarContratoCasoUso;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarContratoCasoUsoImpl implements EliminarContratoCasoUso {

	private DAOFactory daoFactory;

	public EliminarContratoCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ContratoDominio datos) {
		validarObligatoriedadId(datos);
		validarExistenciaContrato(datos);
		eliminarContrato(datos);
	}

	private void validarObligatoriedadId(ContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("El contrato a eliminar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID del contrato es obligatorio.");
		}
	}

	private void validarExistenciaContrato(ContratoDominio datos) {
		com.inmocontrol.entidad.ContratoEntidad existente = daoFactory.obtenerContratoDAO()
				.consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(existente)) {
			throw new ValidacionExcepcion("No existe un contrato con el ID: " + datos.getId());
		}
	}

	private void eliminarContrato(ContratoDominio datos) {
		daoFactory.obtenerContratoDAO().eliminar(datos.getId());
	}
}