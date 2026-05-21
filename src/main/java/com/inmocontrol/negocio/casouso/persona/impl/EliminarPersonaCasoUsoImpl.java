package com.inmocontrol.negocio.casouso.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.negocio.casouso.persona.EliminarPersonaCasoUso;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class EliminarPersonaCasoUsoImpl implements EliminarPersonaCasoUso {

	private DAOFactory daoFactory;

	public EliminarPersonaCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(PersonaDominio datos) {
		validarObligatoriedadId(datos);
		validarExistenciaPersona(datos);
		eliminarPersona(datos);
	}

	private void validarObligatoriedadId(PersonaDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("La persona a eliminar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID de la persona es obligatorio.");
		}
	}

	private void validarExistenciaPersona(PersonaDominio datos) {
		PersonaEntidad existente = daoFactory.obtenerPersonaDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(existente)) {
			throw new ValidacionExcepcion("No existe una persona con el ID: " + datos.getId());
		}
	}

	private void eliminarPersona(PersonaDominio datos) {
		daoFactory.obtenerPersonaDAO().eliminar(datos.getId());
	}
}