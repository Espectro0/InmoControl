package com.inmocontrol.negocio.casouso.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.RegistrarClausulaPorContratoCasoUso;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarClausulaPorContratoCasoUsoImpl implements RegistrarClausulaPorContratoCasoUso {

	private DAOFactory daoFactory;

	public RegistrarClausulaPorContratoCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ClausulaPorContratoDominio datos) {
		validarObligatoriedadCampos(datos);
		registrarClausulaPorContrato(datos);
	}

	private void validarObligatoriedadCampos(ClausulaPorContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("La clausula por contrato a registrar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getNumeroClausula())) {
			throw new ValidacionExcepcion("El numero de clausula es obligatorio.");
		}
		if (UtilObjeto.esNulo(datos.getContrato()) || UtilObjeto.esNulo(datos.getContrato().getId())) {
			throw new ValidacionExcepcion("El contrato es obligatorio.");
		}
		if (UtilObjeto.esNulo(datos.getClausula()) || UtilObjeto.esNulo(datos.getClausula().getId())) {
			throw new ValidacionExcepcion("La clausula es obligatoria.");
		}
	}

	private void registrarClausulaPorContrato(ClausulaPorContratoDominio datos) {
		ClausulaPorContratoEntidad entidad = new ClausulaPorContratoEntidad.Builder()
				.numeroClausula(datos.getNumeroClausula())
				.contrato(new ContratoEntidad.Builder().id(datos.getContrato().getId()).build())
				.clausula(new ClausulaContratoEntidad.Builder().id(datos.getClausula().getId()).build()).build();
		daoFactory.obtenerClausulaPorContratoDAO().crear(entidad);
	}
}