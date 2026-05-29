package com.inmocontrol.negocio.casouso.areareferencia.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.AreaReferenciaEntidad;
import com.inmocontrol.negocio.casouso.areareferencia.ModificarAreaReferenciaCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilValidacion;

public class ModificarAreaReferenciaCasoUsoImpl implements ModificarAreaReferenciaCasoUso {

	private DAOFactory daoFactory;

	public ModificarAreaReferenciaCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(AreaReferenciaDominio datos) {
		validarObligatoriedadId(datos);
		validarExistenciaAreaReferencia(datos);
		validarFormatos(datos);
		validarUnicoNombre(datos);
		modificarAreaReferencia(datos);
	}

	private void validarObligatoriedadId(AreaReferenciaDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("El area de referencia a modificar no es valida.",
					"Validacion fallida en ModificarAreaReferenciaCasoUsoImpl.validarObligatoriedadId() - El area de referencia a modificar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new InmocontrolExcepcion("El ID del area de referencia es obligatorio.",
					"Validacion fallida en ModificarAreaReferenciaCasoUsoImpl.validarObligatoriedadId() - El ID del area de referencia es obligatorio.");
		}
	}

	private void validarExistenciaAreaReferencia(AreaReferenciaDominio datos) {
		AreaReferenciaEntidad existente = daoFactory.obtenerAreaReferenciaDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(existente)) {
			throw new InmocontrolExcepcion("No existe un area de referencia con el ID: " + datos.getId(),
					"Error en ModificarAreaReferenciaCasoUsoImpl.validarExistenciaAreaReferencia() - No existe un area de referencia con el ID: "
							+ datos.getId());
		}
	}

	private void validarFormatos(AreaReferenciaDominio datos) {
		if (!UtilValidacion.validarLongitud(datos.getNombre(), 1, 50)) {
			throw new InmocontrolExcepcion("El nombre del area de referencia debe tener entre 1 y 50 caracteres.",
					"Validacion fallida en ModificarAreaReferenciaCasoUsoImpl.validarFormatos() - El nombre del area de referencia debe tener entre 1 y 50 caracteres.");
		}
	}

	private void validarUnicoNombre(AreaReferenciaDominio datos) {
		AreaReferenciaEntidad filtro = new AreaReferenciaEntidad.Builder().nombre(datos.getNombre()).build();
		var resultados = daoFactory.obtenerAreaReferenciaDAO().consultarPorFiltro(filtro);
		for (AreaReferenciaEntidad item : resultados) {
			if (!item.getId().equals(datos.getId())) {
				throw new InmocontrolExcepcion("Ya existe un area de referencia con el nombre: " + datos.getNombre(),
						"Validacion fallida en ModificarAreaReferenciaCasoUsoImpl.validarUnicoNombre() - Ya existe un area de referencia con el nombre: "
								+ datos.getNombre());
			}
		}
	}

	private void modificarAreaReferencia(AreaReferenciaDominio datos) {
		AreaReferenciaEntidad entidad = new AreaReferenciaEntidad.Builder().id(datos.getId())
				.nombre(UtilSanitizacion.sanitizar(datos.getNombre())).build();
		daoFactory.obtenerAreaReferenciaDAO().actualizar(entidad.getId(), entidad);
	}
}
