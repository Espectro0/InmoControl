package com.inmocontrol.negocio.fachada.persona.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PersonaDTO;
import com.inmocontrol.negocio.casouso.persona.RegistrarPersonaCasoUso;
import com.inmocontrol.negocio.casouso.persona.impl.RegistrarPersonaCasoUsoImpl;
import com.inmocontrol.negocio.dominio.CiudadDominio;
import com.inmocontrol.negocio.dominio.PersonaDominio;
import com.inmocontrol.negocio.dominio.TipoDocumentoDominio;
import com.inmocontrol.negocio.fachada.persona.RegistrarPersonaFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class RegistrarPersonaFachadaImpl implements RegistrarPersonaFachada {

	private DAOFactory daoFactory;
	private RegistrarPersonaCasoUso casoUso;

	public RegistrarPersonaFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new RegistrarPersonaCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(PersonaDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos de la persona no pueden ser nulos",
					"Validacion fallida en RegistrarPersonaFachadaImpl.ejecutar() - Los datos de la persona no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			PersonaDominio dominio = new PersonaDominio.Builder()
					.tipoDocumento(new TipoDocumentoDominio.Builder().id(datos.getTipoDocumento().getId()).build())
					.numeroIdentificacion(datos.getNumeroIdentificacion()).primerNombre(datos.getPrimerNombre())
					.segundoNombre(datos.getSegundoNombre()).primerApellido(datos.getPrimerApellido())
					.segundoApellido(datos.getSegundoApellido()).numeroTelefonico(datos.getNumeroTelefonico())
					.correoElectronico(datos.getCorreoElectronico()).direccionResidencia(datos.getDireccionResidencia())
					.ciudadResidencia(new CiudadDominio.Builder().id(datos.getCiudadResidencia().getId()).build())
					.fechaNacimiento(datos.getFechaNacimiento()).edad(datos.getEdad())					.build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en RegistrarPersonaFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
