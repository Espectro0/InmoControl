package com.inmocontrol.negocio.casouso.areareferencia.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.negocio.casouso.areareferencia.RegistrarAreaReferenciaCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.UtilValidacion;

public class RegistrarAreaReferenciaCasoUsoImpl implements RegistrarAreaReferenciaCasoUso {

	private DAOFactory daoFactory;

	public RegistrarAreaReferenciaCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(AreaReferenciaDominio datos) {
		validarObligatoriedadCampos(datos);
		validarFormatos(datos);
		validarUnicoNombre(datos);
		registrarAreaReferencia(datos);
	}

	private void validarObligatoriedadCampos(AreaReferenciaDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("El area de referencia a registrar no es valida.",
					"Validacion fallida en RegistrarAreaReferenciaCasoUsoImpl.validarObligatoriedadCampos() - El area de referencia a registrar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getNombre()) || datos.getNombre().isEmpty()) {
			throw new InmocontrolExcepcion("El nombre del area de referencia es obligatorio.",
					"Validacion fallida en RegistrarAreaReferenciaCasoUsoImpl.validarObligatoriedadCampos() - El nombre del area de referencia es obligatorio.");
		}
	}

	private void validarFormatos(AreaReferenciaDominio datos) {
		if (!UtilValidacion.validarLongitud(datos.getNombre(), 1, 50)) {
			throw new InmocontrolExcepcion("El nombre del area de referencia debe tener entre 1 y 50 caracteres.",
					"Validacion fallida en RegistrarAreaReferenciaCasoUsoImpl.validarFormatos() - El nombre del area de referencia debe tener entre 1 y 50 caracteres.");
		}
	}

	private void validarUnicoNombre(AreaReferenciaDominio datos) {
		AreaReferenciaEntidad existente = new AreaReferenciaEntidad.Builder().nombre(datos.getNombre()).build();
		var resultados = daoFactory.obtenerAreaReferenciaDAO().consultarPorFiltro(existente);
		if (!resultados.isEmpty()) {
			throw new InmocontrolExcepcion("Ya existe un area de referencia con el nombre: " + datos.getNombre(),
					"Validacion fallida en RegistrarAreaReferenciaCasoUsoImpl.validarUnicoNombre() - Ya existe un area de referencia con el nombre: "
							+ datos.getNombre());
		}
	}

	private void registrarAreaReferencia(AreaReferenciaDominio datos) {
		var idUnico = UtilUUID.generarUnico(uuid -> daoFactory.obtenerAreaReferenciaDAO().consultarPorId(uuid) != null);
		AreaReferenciaEntidad entidad = new AreaReferenciaEntidad.Builder().id(idUnico)
				.nombre(UtilSanitizacion.sanitizar(datos.getNombre())).build();
		daoFactory.obtenerAreaReferenciaDAO().crear(entidad);
	}
}
