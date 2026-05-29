package com.inmocontrol.negocio.casouso.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.negocio.casouso.clausulacontrato.ModificarClausulaContratoCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilValidacion;

public class ModificarClausulaContratoCasoUsoImpl implements ModificarClausulaContratoCasoUso {

	private DAOFactory daoFactory;

	public ModificarClausulaContratoCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ClausulaContratoDominio datos) {
		validarObligatoriedadId(datos);
		validarExistenciaClausulaContrato(datos);
		validarFormatos(datos);
		validarUnicoAreaTipoTitulo(datos);
		modificarClausulaContrato(datos);
	}

	private void validarObligatoriedadId(ClausulaContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("La clausula contrato a modificar no es valida.",
					"Validacion fallida en ModificarClausulaContratoCasoUsoImpl.validarObligatoriedadId() - La clausula contrato a modificar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new InmocontrolExcepcion("El ID de la clausula contrato es obligatorio.",
					"Validacion fallida en ModificarClausulaContratoCasoUsoImpl.validarObligatoriedadId() - El ID de la clausula contrato es obligatorio.");
		}
	}

	private void validarExistenciaClausulaContrato(ClausulaContratoDominio datos) {
		ClausulaContratoEntidad existente = daoFactory.obtenerClausulaContratoDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(existente)) {
			throw new InmocontrolExcepcion("No existe una clausula contrato con el ID: " + datos.getId(),
					"Error en ModificarClausulaContratoCasoUsoImpl.validarExistenciaClausulaContrato() - No existe una clausula contrato con el ID: "
							+ datos.getId());
		}
	}

	private void validarFormatos(ClausulaContratoDominio datos) {
		if (!UtilValidacion.validarLongitud(datos.getTitulo(), 1, 50)) {
			throw new InmocontrolExcepcion("El titulo debe tener entre 1 y 50 caracteres.",
					"Validacion fallida en ModificarClausulaContratoCasoUsoImpl.validarFormatos() - El titulo debe tener entre 1 y 50 caracteres.");
		}
		if (!UtilValidacion.validarLongitud(datos.getContenidoLegal(), 1, 200)) {
			throw new InmocontrolExcepcion("El contenido legal debe tener entre 1 y 200 caracteres.",
					"Validacion fallida en ModificarClausulaContratoCasoUsoImpl.validarFormatos() - El contenido legal debe tener entre 1 y 200 caracteres.");
		}
	}

	private void validarUnicoAreaTipoTitulo(ClausulaContratoDominio datos) {
		ClausulaContratoEntidad filtro = new ClausulaContratoEntidad.Builder()
				.areaReferencia(datos.getAreaReferencia() != null
						? new AreaReferenciaEntidad.Builder().id(datos.getAreaReferencia().getId()).build()
						: null)
				.tipoAplicacion(datos.getTipoAplicacion() != null
						? new TipoAplicacionEntidad.Builder().id(datos.getTipoAplicacion().getId()).build()
						: null)
				.titulo(datos.getTitulo()).build();
		var resultados = daoFactory.obtenerClausulaContratoDAO().consultarPorFiltro(filtro);
		for (ClausulaContratoEntidad item : resultados) {
			if (!item.getId().equals(datos.getId())) {
				throw new InmocontrolExcepcion(
						"Ya existe una clausula con el area, tipo de aplicacion y titulo especificados",
						"Validacion fallida en ModificarClausulaContratoCasoUsoImpl.validarUnicoAreaTipoTitulo() - Ya existe una clausula con el area, tipo de aplicacion y titulo especificados");
			}
		}
	}

	private void modificarClausulaContrato(ClausulaContratoDominio dominio) {
		ClausulaContratoEntidad entidad = new ClausulaContratoEntidad.Builder().id(dominio.getId())
				.areaReferencia(dominio.getAreaReferencia() != null
						? new AreaReferenciaEntidad.Builder().id(dominio.getAreaReferencia().getId()).build()
						: null)
				.tipoAplicacion(dominio.getTipoAplicacion() != null
						? new TipoAplicacionEntidad.Builder().id(dominio.getTipoAplicacion().getId()).build()
						: null)
				.titulo(UtilSanitizacion.sanitizar(dominio.getTitulo()))
				.contenidoLegal(UtilSanitizacion.sanitizar(dominio.getContenidoLegal())).build();
		daoFactory.obtenerClausulaContratoDAO().actualizar(entidad.getId(), entidad);
	}
}
