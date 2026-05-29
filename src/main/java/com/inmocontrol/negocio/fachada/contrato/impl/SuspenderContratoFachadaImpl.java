package com.inmocontrol.negocio.fachada.contrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ContratoDTO;
import com.inmocontrol.negocio.casouso.contrato.SuspenderContratoCasoUso;
import com.inmocontrol.negocio.casouso.contrato.impl.SuspenderContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.ContratoDominio;
import com.inmocontrol.negocio.fachada.contrato.SuspenderContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

public class SuspenderContratoFachadaImpl implements SuspenderContratoFachada {

	private DAOFactory daoFactory;
	private SuspenderContratoCasoUso casoUso;

	public SuspenderContratoFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new SuspenderContratoCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(ContratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del contrato no pueden ser nulos",
					"Validacion fallida en SuspenderContratoFachadaImpl.ejecutar() - Los datos del contrato no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			ContratoDominio dominio = new ContratoDominio.Builder().id(datos.getId()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en SuspenderContratoFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
