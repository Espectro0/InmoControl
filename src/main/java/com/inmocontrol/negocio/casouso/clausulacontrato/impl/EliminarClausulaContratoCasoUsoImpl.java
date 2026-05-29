package com.inmocontrol.negocio.casouso.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.entidad.ClausulaPorContratoEntidad;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.negocio.casouso.clausulacontrato.EliminarClausulaContratoCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;

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
		validarNoEstaEnContratosActivos(datos);
		eliminarClausulaContrato(datos);
	}

	private void validarObligatoriedadId(ClausulaContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("La clausula contrato a eliminar no es valida.",
					"Validacion fallida en EliminarClausulaContratoCasoUsoImpl.validarObligatoriedadId() - La clausula contrato a eliminar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new InmocontrolExcepcion("El ID de la clausula contrato es obligatorio.",
					"Validacion fallida en EliminarClausulaContratoCasoUsoImpl.validarObligatoriedadId() - El ID de la clausula contrato es obligatorio.");
		}
	}

	private void validarExistenciaClausulaContrato(ClausulaContratoDominio datos) {
		ClausulaContratoEntidad existente = daoFactory.obtenerClausulaContratoDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(existente)) {
			throw new InmocontrolExcepcion("No existe una clausula contrato con el ID: " + datos.getId(),
					"Error en EliminarClausulaContratoCasoUsoImpl.validarExistenciaClausulaContrato() - No existe una clausula contrato con el ID: "
							+ datos.getId());
		}
	}

	private void validarNoEstaEnContratosActivos(ClausulaContratoDominio datos) {
		ClausulaPorContratoEntidad filtro = new ClausulaPorContratoEntidad.Builder()
				.clausula(new ClausulaContratoEntidad.Builder().id(datos.getId()).build()).build();
		var clausulasPorContrato = daoFactory.obtenerClausulaPorContratoDAO().consultarPorFiltro(filtro);
		if (!clausulasPorContrato.isEmpty()) {
			for (ClausulaPorContratoEntidad cpc : clausulasPorContrato) {
				if (cpc.getContrato() != null && cpc.getContrato().getId() != null) {
					ContratoEntidad contrato = daoFactory.obtenerContratoDAO()
							.consultarPorId(cpc.getContrato().getId());
					if (contrato != null && Boolean.TRUE.equals(contrato.getEsActivo())) {
						throw new InmocontrolExcepcion(
								"No es posible eliminar la clausula contrato porque esta asignada a un contrato activo.",
								"Validacion fallida en EliminarClausulaContratoCasoUsoImpl.validarNoEstaEnContratosActivos() - No es posible eliminar la clausula contrato porque esta asignada a un contrato activo.");
					}
				}
			}
		}
	}

	private void eliminarClausulaContrato(ClausulaContratoDominio datos) {
		daoFactory.obtenerClausulaContratoDAO().eliminar(datos.getId());
	}
}
