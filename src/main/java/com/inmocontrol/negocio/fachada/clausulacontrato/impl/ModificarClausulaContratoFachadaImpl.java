package com.inmocontrol.negocio.fachada.clausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.ClausulaContratoDTO;
import com.inmocontrol.negocio.casouso.clausulacontrato.ModificarClausulaContratoCasoUso;
import com.inmocontrol.negocio.casouso.clausulacontrato.impl.ModificarClausulaContratoCasoUsoImpl;
import com.inmocontrol.negocio.dominio.AreaReferenciaDominio;
import com.inmocontrol.negocio.dominio.ClausulaContratoDominio;
import com.inmocontrol.negocio.dominio.TipoAplicacionDominio;
import com.inmocontrol.negocio.fachada.clausulacontrato.ModificarClausulaContratoFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import com.inmocontrol.transversal.excepcion.ValidadorExcepcion;

public class ModificarClausulaContratoFachadaImpl implements ModificarClausulaContratoFachada {

	private DAOFactory daoFactory;
	private ModificarClausulaContratoCasoUso casoUso;

	public ModificarClausulaContratoFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ModificarClausulaContratoCasoUsoImpl(daoFactory);
	}

	@Override
	public void ejecutar(ClausulaContratoDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos de la clausula contrato no pueden ser nulos",
					"Validacion fallida en ModificarClausulaContratoFachadaImpl.ejecutar() - Los datos de la clausula contrato no pueden ser nulos");
		}

		try {
			daoFactory.iniciarTransaccion();
			ClausulaContratoDominio dominio = new ClausulaContratoDominio.Builder().id(datos.getId())
					.areaReferencia(new AreaReferenciaDominio.Builder().id(datos.getAreaReferencia().getId()).build())
					.tipoAplicacion(new TipoAplicacionDominio.Builder().id(datos.getTipoAplicacion().getId()).build())
					.titulo(datos.getTitulo()).contenidoLegal(datos.getContenidoLegal()).build();
			casoUso.ejecutar(dominio);
			daoFactory.confirmarTransaccion();

		} catch (Exception excepcion) {
			daoFactory.cancelarTransaccion();
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ModificarClausulaContratoFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
