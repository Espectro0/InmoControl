package com.inmocontrol.negocio.casouso.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.contrato.RegistrarContratoCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.UtilValidacion;

public class RegistrarContratoCasoUsoImpl implements RegistrarContratoCasoUso {

	private DAOFactory daoFactory;

	public RegistrarContratoCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ContratoDominio datos) {
		validarObligatoriedadCampos(datos);
		validarFormatos(datos);
		validarFechas(datos);
		validarUnicoCodigoContrato(datos);
		registrarContrato(datos);
	}

	private void validarObligatoriedadCampos(ContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("El contrato a registrar no es valido.",
					"Validacion fallida en RegistrarContratoCasoUsoImpl.validarObligatoriedadCampos() - El contrato a registrar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getCodigoContrato()) || datos.getCodigoContrato().isEmpty()) {
			throw new InmocontrolExcepcion("El codigo del contrato es obligatorio.",
					"Validacion fallida en RegistrarContratoCasoUsoImpl.validarObligatoriedadCampos() - El codigo del contrato es obligatorio.");
		}
		if (UtilObjeto.esNulo(datos.getFechaInicio())) {
			throw new InmocontrolExcepcion("La fecha de inicio es obligatoria.",
					"Validacion fallida en RegistrarContratoCasoUsoImpl.validarObligatoriedadCampos() - La fecha de inicio es obligatoria.");
		}
		if (UtilObjeto.esNulo(datos.getPropiedad().getId())) {
			throw new InmocontrolExcepcion("La propiedad es obligatoria.",
					"Validacion fallida en RegistrarContratoCasoUsoImpl.validarObligatoriedadCampos() - La propiedad es obligatoria.");
		}
	}

	private void validarFormatos(ContratoDominio datos) {
		if (!UtilValidacion.validarLongitud(datos.getCodigoContrato(), 1, 15)) {
			throw new InmocontrolExcepcion("El codigo del contrato debe tener entre 1 y 15 caracteres.",
					"Validacion fallida en RegistrarContratoCasoUsoImpl.validarFormatos() - El codigo del contrato debe tener entre 1 y 15 caracteres.");
		}
	}

	private void validarFechas(ContratoDominio datos) {
		if (datos.getFechaFin() != null && datos.getFechaInicio().compareTo(datos.getFechaFin()) >= 0) {
			throw new InmocontrolExcepcion("La fecha de fin debe ser estrictamente mayor a la fecha de inicio.",
					"Validacion fallida en RegistrarContratoCasoUsoImpl.validarFechas() - La fecha de fin debe ser estrictamente mayor a la fecha de inicio.");
		}
	}

	private void validarUnicoCodigoContrato(ContratoDominio datos) {
		ContratoEntidad filtro = new ContratoEntidad.Builder().codigoContrato(datos.getCodigoContrato()).build();
		var resultados = daoFactory.obtenerContratoDAO().consultarPorFiltro(filtro);
		if (!resultados.isEmpty()) {
			throw new InmocontrolExcepcion("Ya existe un contrato con el codigo: " + datos.getCodigoContrato(),
					"Validacion fallida en RegistrarContratoCasoUsoImpl.validarUnicoCodigoContrato() - Ya existe un contrato con el codigo: "
							+ datos.getCodigoContrato());
		}
	}

	private void registrarContrato(ContratoDominio dominio) {
		var idUnico = UtilUUID.generarUnico(uuid -> daoFactory.obtenerContratoDAO().consultarPorId(uuid) != null);
		ContratoEntidad entidad = new ContratoEntidad.Builder().id(idUnico)
				.codigoContrato(UtilSanitizacion.sanitizar(dominio.getCodigoContrato()))
				.fechaInicio(dominio.getFechaInicio()).fechaFin(dominio.getFechaFin())
				.esActivo(dominio.getEsActivo() != null ? dominio.getEsActivo() : false)
				.propiedad(new PropiedadEntidad.Builder().id(dominio.getPropiedad().getId()).build()).build();
		daoFactory.obtenerContratoDAO().crear(entidad);
	}
}
