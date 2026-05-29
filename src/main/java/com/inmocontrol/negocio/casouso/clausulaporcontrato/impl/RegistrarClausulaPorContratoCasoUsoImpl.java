package com.inmocontrol.negocio.casouso.clausulaporcontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulaporcontrato.RegistrarClausulaPorContratoCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.UtilValidacion;


public class RegistrarClausulaPorContratoCasoUsoImpl implements RegistrarClausulaPorContratoCasoUso {

	private DAOFactory daoFactory;

	public RegistrarClausulaPorContratoCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ClausulaPorContratoDominio datos) {
		validarObligatoriedadCampos(datos);
		validarFormatos(datos);
		validarUnicoContratoClausula(datos);
		registrarClausulaPorContrato(datos);
	}

	private void validarObligatoriedadCampos(ClausulaPorContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("La clausula por contrato a registrar no es valida.",
					"Validacion fallida en RegistrarClausulaPorContratoCasoUsoImpl.validarObligatoriedadCampos() - La clausula por contrato a registrar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getNumeroClausula())) {
			throw new InmocontrolExcepcion("El numero de clausula es obligatorio.",
					"Validacion fallida en RegistrarClausulaPorContratoCasoUsoImpl.validarObligatoriedadCampos() - El numero de clausula es obligatorio.");
		}
		if (UtilObjeto.esNulo(datos.getContrato().getId())) {
			throw new InmocontrolExcepcion("El contrato es obligatorio.",
					"Validacion fallida en RegistrarClausulaPorContratoCasoUsoImpl.validarObligatoriedadCampos() - El contrato es obligatorio.");
		}
		if (UtilObjeto.esNulo(datos.getClausula().getId())) {
			throw new InmocontrolExcepcion("La clausula es obligatoria.",
					"Validacion fallida en RegistrarClausulaPorContratoCasoUsoImpl.validarObligatoriedadCampos() - La clausula es obligatoria.");
		}
	}

	private void validarFormatos(ClausulaPorContratoDominio datos) {
		if (!UtilValidacion.validarRangoEntero(datos.getNumeroClausula(), 1, 40)) {
			throw new InmocontrolExcepcion("El numero de clausula debe estar entre 1 y 40.",
					"Validacion fallida en RegistrarClausulaPorContratoCasoUsoImpl.validarFormatos() - El numero de clausula debe estar entre 1 y 40.");
		}
	}

	private void registrarClausulaPorContrato(ClausulaPorContratoDominio dominio) {
		var idUnico = UtilUUID
				.generarUnico(uuid -> daoFactory.obtenerClausulaPorContratoDAO().consultarPorId(uuid) != null);
		ClausulaPorContratoEntidad entidad = new ClausulaPorContratoEntidad.Builder().id(idUnico)
				.numeroClausula(dominio.getNumeroClausula())
				.contrato(new ContratoEntidad.Builder().id(dominio.getContrato().getId()).build())
				.clausula(new ClausulaContratoEntidad.Builder().id(dominio.getClausula().getId()).build()).build();
		daoFactory.obtenerClausulaPorContratoDAO().crear(entidad);
	}

	private void validarUnicoContratoClausula(ClausulaPorContratoDominio datos) {
		ClausulaPorContratoEntidad filtro = new ClausulaPorContratoEntidad.Builder()
				.contrato(new ContratoEntidad.Builder().id(datos.getContrato().getId()).build())
				.clausula(new ClausulaContratoEntidad.Builder().id(datos.getClausula().getId()).build()).build();
		var resultados = daoFactory.obtenerClausulaPorContratoDAO().consultarPorFiltro(filtro);
		if (!resultados.isEmpty()) {
			throw new InmocontrolExcepcion("Ya existe esta clausula agregada a este contrato.",
					"Validacion fallida en RegistrarClausulaPorContratoCasoUsoImpl.validarUnicoContratoClausula() - Ya existe esta clausula agregada a este contrato.");
		}
	}
}
