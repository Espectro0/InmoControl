package com.inmocontrol.negocio.casouso.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.entidad.ClausulaContratoEntidad;
import com.inmocontrol.entidad.TipoAplicacionEntidad;
import com.inmocontrol.negocio.casouso.clausulacontrato.RegistrarClausulaContratoCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.UtilValidacion;
import com.inmocontrol.transversal.excepcion.ValidadorExcepcion;

public class RegistrarClausulaContratoCasoUsoImpl implements RegistrarClausulaContratoCasoUso {

	private DAOFactory daoFactory;

	public RegistrarClausulaContratoCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ClausulaContratoDominio datos) {
		validarObligatoriedadCampos(datos);
		validarFormatos(datos);
		validarUnicoAreaTipoTitulo(datos);
		registrarClausulaContrato(datos);
	}

	private void validarObligatoriedadCampos(ClausulaContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("La clausula contrato a registrar no es valida.",
					"Validacion fallida en RegistrarClausulaContratoCasoUsoImpl.validarObligatoriedadCampos() - La clausula contrato a registrar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getAreaReferencia().getId())) {
			throw new InmocontrolExcepcion("El area de referencia es obligatorio.",
					"Validacion fallida en RegistrarClausulaContratoCasoUsoImpl.validarObligatoriedadCampos() - El area de referencia es obligatoria.");
		}
		if (UtilObjeto.esNulo(datos.getTipoAplicacion().getId())) {
			throw new InmocontrolExcepcion("El tipo de aplicacion es obligatorio.",
					"Validacion fallida en RegistrarClausulaContratoCasoUsoImpl.validarObligatoriedadCampos() - El tipo de aplicacion es obligatorio.");
		}
		if (UtilObjeto.esNulo(datos.getTitulo()) || datos.getTitulo().isEmpty()) {
			throw new InmocontrolExcepcion("El titulo es obligatorio.",
					"Validacion fallida en RegistrarClausulaContratoCasoUsoImpl.validarObligatoriedadCampos() - El titulo es obligatorio.");
		}
		if (UtilObjeto.esNulo(datos.getContenidoLegal()) || datos.getContenidoLegal().isEmpty()) {
			throw new InmocontrolExcepcion("El contenido legal es obligatorio.",
					"Validacion fallida en RegistrarClausulaContratoCasoUsoImpl.validarObligatoriedadCampos() - El contenido legal es obligatorio.");
		}
	}

	private void validarFormatos(ClausulaContratoDominio datos) {
		if (!UtilValidacion.validarLongitud(datos.getTitulo(), 1, 50)) {
			throw new InmocontrolExcepcion("El titulo debe tener entre 1 y 50 caracteres.",
					"Validacion fallida en RegistrarClausulaContratoCasoUsoImpl.validarFormatos() - El titulo debe tener entre 1 y 50 caracteres.");
		}
		if (!UtilValidacion.validarLongitud(datos.getContenidoLegal(), 1, 200)) {
			throw new InmocontrolExcepcion("El contenido legal debe tener entre 1 y 200 caracteres.",
					"Validacion fallida en RegistrarClausulaContratoCasoUsoImpl.validarFormatos() - El contenido legal debe tener entre 1 y 200 caracteres.");
		}
	}

	private void validarUnicoAreaTipoTitulo(ClausulaContratoDominio datos) {
		ClausulaContratoEntidad filtro = new ClausulaContratoEntidad.Builder()
				.areaReferencia(new AreaReferenciaEntidad.Builder().id(datos.getAreaReferencia().getId()).build())
				.tipoAplicacion(new TipoAplicacionEntidad.Builder().id(datos.getTipoAplicacion().getId()).build())
				.titulo(datos.getTitulo()).build();
		var resultados = daoFactory.obtenerClausulaContratoDAO().consultarPorFiltro(filtro);
		if (!resultados.isEmpty()) {
			throw new InmocontrolExcepcion(
					"Ya existe una clausula con el area, tipo de aplicacion y titulo especificados",
					"Validacion fallida en RegistrarClausulaContratoCasoUsoImpl.validarUnicoAreaTipoTitulo() - Ya existe una clausula con el area, tipo de aplicacion y titulo especificados");
		}
	}

	private void registrarClausulaContrato(ClausulaContratoDominio dominio) {
		if (dominio.getAreaReferencia().getId().equals(UtilUUID.UUID_CERO)) {
			throw new ValidadorExcepcion("El área de referencia es obligatoria");
		}
		if (dominio.getAreaReferencia().getNombre() != null && dominio.getAreaReferencia().getNombre().equals("N/A")) {
			throw new ValidadorExcepcion("El área de referencia es obligatoria");
		}
		if (dominio.getTipoAplicacion().getId().equals(UtilUUID.UUID_CERO)) {
			throw new ValidadorExcepcion("El tipo de aplicación es obligatorio");
		}
		if (dominio.getTipoAplicacion().getNombre() != null && dominio.getTipoAplicacion().getNombre().equals("N/A")) {
			throw new ValidadorExcepcion("El tipo de aplicación es obligatorio");
		}
		var idUnico = UtilUUID
				.generarUnico(uuid -> daoFactory.obtenerClausulaContratoDAO().consultarPorId(uuid) != null);
		ClausulaContratoEntidad entidad = new ClausulaContratoEntidad.Builder().id(idUnico)
				.areaReferencia(new AreaReferenciaEntidad.Builder().id(datos.getAreaReferencia().getId()).build())
				.tipoAplicacion(new TipoAplicacionEntidad.Builder().id(datos.getTipoAplicacion().getId()).build())
				.titulo(UtilSanitizacion.sanitizar(datos.getTitulo()))
				.contenidoLegal(UtilSanitizacion.sanitizar(datos.getContenidoLegal())).build();
		daoFactory.obtenerClausulaContratoDAO().crear(entidad);
	}
}
