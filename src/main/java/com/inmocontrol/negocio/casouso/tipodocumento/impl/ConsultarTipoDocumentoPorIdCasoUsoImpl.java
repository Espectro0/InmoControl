package com.inmocontrol.negocio.casouso.tipodocumento.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoDocumentoEntidad;
import com.inmocontrol.negocio.casouso.tipodocumento.ConsultarTipoDocumentoPorIdCasoUso;
import com.inmocontrol.negocio.dominio.TipoDocumentoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.ValidacionExcepcion;

public class ConsultarTipoDocumentoPorIdCasoUsoImpl implements ConsultarTipoDocumentoPorIdCasoUso {

	private DAOFactory daoFactory;

	public ConsultarTipoDocumentoPorIdCasoUsoImpl(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public TipoDocumentoEntidad ejecutar(TipoDocumentoDominio datos) {
		validarObligatoriedadIdTipoDocumento(datos);
		return consultarTipoDocumento(datos);
	}

	private void validarObligatoriedadIdTipoDocumento(TipoDocumentoDominio datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new ValidacionExcepcion("El tipo de documento a consultar no es valido.");
		}
		if (UtilObjeto.esNulo(datos.getId())) {
			throw new ValidacionExcepcion("El ID del tipo de documento es obligatorio.");
		}
	}

	private TipoDocumentoEntidad consultarTipoDocumento(TipoDocumentoDominio datos) {
		TipoDocumentoEntidad tipoDocumentoEntidad = daoFactory.obtenerTipoDocumentoDAO().consultarPorId(datos.getId());
		if (UtilObjeto.esNulo(tipoDocumentoEntidad)) {
			throw new ValidacionExcepcion("No existe un tipo de documento con el ID: " + datos.getId());
		}
		return tipoDocumentoEntidad;
	}
}