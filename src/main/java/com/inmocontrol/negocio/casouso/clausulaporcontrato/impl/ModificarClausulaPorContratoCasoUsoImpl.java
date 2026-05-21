package com.inmocontrol.negocio.casouso.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.ModificarClausulaPorContratoCasoUso;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ModificarClausulaPorContratoCasoUsoImpl implements ModificarClausulaPorContratoCasoUso {

	private DAOFactory daoFactory;

	public ModificarClausulaPorContratoCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ClausulaPorContratoDominio datos) {
		validarObligatoriedadId(datos);
		validarExistenciaClausulaPorContrato(datos);
		modificarClausulaPorContrato(datos);
	}

	private void validarObligatoriedadId(ClausulaPorContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("La clausula por contrato a modificar no es valida.");
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

	private void modificarClausulaPorContrato(ClausulaPorContratoDominio datos) {
		ClausulaPorContratoEntidad entidad = new ClausulaPorContratoEntidad.Builder().id(datos.getId())
				.numeroClausula(datos.getNumeroClausula())
				.contrato(datos.getContrato() != null
						? new ContratoEntidad.Builder().id(datos.getContrato().getId()).build()
						: null)
				.clausula(datos.getClausula() != null
						? new ClausulaContratoEntidad.Builder().id(datos.getClausula().getId()).build()
						: null)
				.build();
		daoFactory.obtenerClausulaPorContratoDAO().actualizar(entidad.getId(), entidad);
	}
}