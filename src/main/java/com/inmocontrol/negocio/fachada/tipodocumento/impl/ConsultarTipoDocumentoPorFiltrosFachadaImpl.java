package com.inmocontrol.negocio.fachada.tipodocumento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.TipoDocumentoDTO;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.casouso.tipodocumento.ConsultarTipoDocumentoPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.tipodocumento.impl.ConsultarTipoDocumentoPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.TipoDocumentoDominio;
import com.inmocontrol.negocio.fachada.tipodocumento.ConsultarTipoDocumentoPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

import java.util.List;

public class ConsultarTipoDocumentoPorFiltrosFachadaImpl implements ConsultarTipoDocumentoPorFiltrosFachada {

	private DAOFactory daoFactory;
	private ConsultarTipoDocumentoPorFiltrosCasoUso casoUso;

	public ConsultarTipoDocumentoPorFiltrosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarTipoDocumentoPorFiltrosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<TipoDocumentoEntidad> ejecutar(TipoDocumentoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del tipo de documento no pueden ser nulos",
					"Validacion fallida en ConsultarTipoDocumentoPorFiltrosFachadaImpl.ejecutar() - Los datos del tipo de documento no pueden ser nulos");
		}

		try {
			TipoDocumentoDominio dominio = new TipoDocumentoDominio.Builder().nombre(datos.getNombre()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarTipoDocumentoPorFiltrosFachadaImpl.ejecutar() - " + excepcion.getMessage(),
					excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
