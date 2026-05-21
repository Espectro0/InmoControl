package com.inmocontrol.negocio.casouso.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PersonaEntidad;
import com.inmocontrol.negocio.casouso.persona.ConsultarPersonaPorIdCasoUso;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarPersonaPorIdCasoUsoImpl implements ConsultarPersonaPorIdCasoUso {

	private DAOFactory daoFactory;

	public ConsultarPersonaPorIdCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public PersonaEntidad ejecutar(PersonaDominio datos) {
		validarObligatoriedadIdPersona(datos);
		return consultarPersona(datos);
	}

	private void validarObligatoriedadIdPersona(PersonaDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("La persona a consultar no es valida.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID de la persona es obligatorio.");
		}
	}

	private PersonaEntidad consultarPersona(PersonaDominio datos) {
		PersonaEntidad personaEntidad = daoFactory.obtenerPersonaDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(personaEntidad)) {
			throw new ValidacionExcepcion("No existe una persona con el ID: " + datos.getId());
		}
		return personaEntidad;
	}
}