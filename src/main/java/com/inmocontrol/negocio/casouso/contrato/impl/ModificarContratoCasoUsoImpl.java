package com.inmocontrol.negocio.casouso.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ContratoEntidad;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.contrato.ModificarContratoCasoUso;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilSanitizacion;
import com.inmocontrol.transversal.UtilValidacion;

public class ModificarContratoCasoUsoImpl implements ModificarContratoCasoUso {

	private DAOFactory daoFactory;

	public ModificarContratoCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ejecutar(ContratoDominio datos) {
		validarObligatoriedadId(datos);
		validarExistenciaContrato(datos);
		validarFormatos(datos);
		validarFechas(datos);
		validarUnicoCodigoContrato(datos);
		modificarContrato(datos);
	}

	private void validarObligatoriedadId(ContratoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("El contrato a modificar no es valido.",
					"Validacion fallida en ModificarContratoCasoUsoImpl.validarObligatoriedadId() - El contrato a modificar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new InmocontrolExcepcion("El ID del contrato es obligatorio.",
					"Validacion fallida en ModificarContratoCasoUsoImpl.validarObligatoriedadId() - El ID del contrato es obligatorio.");
		}
	}

	private void validarExistenciaContrato(ContratoDominio datos) {
		ContratoEntidad existente = daoFactory.obtenerContratoDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(existente)) {
			throw new InmocontrolExcepcion("No existe un contrato con el ID: " + datos.getId(),
					"Error en ModificarContratoCasoUsoImpl.validarExistenciaContrato() - No existe un contrato con el ID: "
							+ datos.getId());
		}
	}

	private void validarFormatos(ContratoDominio datos) {
		if (!UtilValidacion.validarLongitud(datos.getCodigoContrato(), 1, 15)) {
			throw new InmocontrolExcepcion("El codigo del contrato debe tener entre 1 y 15 caracteres.",
					"Validacion fallida en ModificarContratoCasoUsoImpl.validarFormatos() - El codigo del contrato debe tener entre 1 y 15 caracteres.");
		}
	}

	private void validarFechas(ContratoDominio datos) {
		ContratoEntidad existente = daoFactory.obtenerContratoDAO().consultarPorId(datos.getId());
		java.util.Date fechaInicio = datos.getFechaInicio() != null ? datos.getFechaInicio()
				: existente.getFechaInicio();
		java.util.Date fechaFin = datos.getFechaFin() != null ? datos.getFechaFin() : existente.getFechaFin();
		if (fechaFin != null && fechaInicio != null && fechaInicio.after(fechaFin)) {
			throw new InmocontrolExcepcion("La fecha de inicio debe ser anterior a la fecha de fin.",
					"Validacion fallida en ModificarContratoCasoUsoImpl.validarFechas() - La fecha de inicio debe ser anterior a la fecha de fin.");
		}
	}

	private void validarUnicoCodigoContrato(ContratoDominio datos) {
		ContratoEntidad filtro = new ContratoEntidad.Builder().codigoContrato(datos.getCodigoContrato()).build();
		var resultados = daoFactory.obtenerContratoDAO().consultarPorFiltro(filtro);
		for (ContratoEntidad item : resultados) {
			if (!item.getId().equals(datos.getId())) {
				throw new InmocontrolExcepcion("Ya existe un contrato con el codigo: " + datos.getCodigoContrato(),
						"Validacion fallida en ModificarContratoCasoUsoImpl.validarUnicoCodigoContrato() - Ya existe un contrato con el codigo: "
								+ datos.getCodigoContrato());
			}
		}
	}

	private void modificarContrato(ContratoDominio dominio) {
		ContratoEntidad entidad = new ContratoEntidad.Builder().id(dominio.getId())
				.codigoContrato(UtilSanitizacion.sanitizar(dominio.getCodigoContrato()))
				.fechaInicio(dominio.getFechaInicio()).fechaFin(dominio.getFechaFin()).esActivo(dominio.getEsActivo())
				.propiedad(new PropiedadEntidad.Builder().id(dominio.getPropiedad().getId()).build())
				.build();
		daoFactory.obtenerContratoDAO().actualizar(entidad.getId(), entidad);
	}
}
