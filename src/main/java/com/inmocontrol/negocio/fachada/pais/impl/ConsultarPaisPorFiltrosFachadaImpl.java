package com.inmocontrol.negocio.fachada.pais.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.dto.PaisDTO;
import com.inmocontrol.entidad.PaisEntidad;
import com.inmocontrol.negocio.casouso.pais.ConsultarPaisPorFiltrosCasoUso;
import com.inmocontrol.negocio.casouso.pais.impl.ConsultarPaisPorFiltrosCasoUsoImpl;
import com.inmocontrol.negocio.dominio.PaisDominio;
import com.inmocontrol.negocio.fachada.pais.ConsultarPaisPorFiltrosFachada;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;

import java.util.List;

public class ConsultarPaisPorFiltrosFachadaImpl implements ConsultarPaisPorFiltrosFachada {

	private com.inmocontrol.datos.dao.sql.factoria.DAOFactory daoFactory;
	private ConsultarPaisPorFiltrosCasoUso casoUso;

	public ConsultarPaisPorFiltrosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarPaisPorFiltrosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<PaisEntidad> ejecutar(PaisDTO datos) {
		if (UtilObjeto.esNulo(datos)) {
			throw new InmocontrolExcepcion("Los datos del pais no pueden ser nulos",
					"Validacion fallida en ConsultarPaisPorFiltrosFachadaImpl.ejecutar() - Los datos del pais no pueden ser nulos");
		}

		try {
			PaisDominio dominio = new PaisDominio.Builder().nombre(datos.getNombre()).build();
			return casoUso.ejecutar(dominio);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("No se pudo completar la operacion. Intente mas tarde.",
					"Error en ConsultarPaisPorFiltrosFachadaImpl.ejecutar() - " + excepcion.getMessage(), excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
