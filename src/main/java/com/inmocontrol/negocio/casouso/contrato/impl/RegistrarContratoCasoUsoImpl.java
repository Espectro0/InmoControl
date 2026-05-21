package com.inmocontrol.negocio.casouso.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.contrato.RegistrarContratoCasoUso;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class RegistrarContratoCasoUsoImpl implements RegistrarContratoCasoUso {

	private DAOFactory daoFactory;

	public RegistrarContratoCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ContratoDominio datos) {
		validarObligatoriedadCampos(datos);
		registrarContrato(datos);
	}

	private void validarObligatoriedadCampos(ContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("El contrato a registrar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getCodigoContrato()) || datos.getCodigoContrato().isEmpty()) {
			throw new ValidacionExcepcion("El codigo del contrato es obligatorio.");
		}
		if (UtilObjeto.esNulo(datos.getFechaInicio())) {
			throw new ValidacionExcepcion("La fecha de inicio es obligatoria.");
		}
		if (UtilObjeto.esNulo(datos.getPropiedad()) || UtilObjeto.esNulo(datos.getPropiedad().getId())) {
			throw new ValidacionExcepcion("La propiedad es obligatoria.");
		}
	}

	private void registrarContrato(ContratoDominio datos) {
		ContratoEntidad entidad = new ContratoEntidad.Builder().codigoContrato(datos.getCodigoContrato())
				.fechaInicio(datos.getFechaInicio()).fechaFin(datos.getFechaFin()).esActivo(true)
				.propiedad(new PropiedadEntidad.Builder().id(datos.getPropiedad().getId()).build()).build();
		daoFactory.obtenerContratoDAO().crear(entidad);
	}
}