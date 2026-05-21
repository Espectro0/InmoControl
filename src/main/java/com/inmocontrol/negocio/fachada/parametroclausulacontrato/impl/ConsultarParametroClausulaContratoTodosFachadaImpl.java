package com.inmocontrol.negocio.fachada.parametroclausulacontrato.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.ParametroClausulaContratoEntidad;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.impl.ConsultarParametroClausulaContratoTodosCasoUsoImpl;
import com.inmocontrol.negocio.casouso.parametroclausulacontrato.ConsultarParametroClausulaContratoTodosCasoUso;
import com.inmocontrol.negocio.fachada.parametroclausulacontrato.ConsultarParametroClausulaContratoTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarParametroClausulaContratoTodosFachadaImpl
		implements ConsultarParametroClausulaContratoTodosFachada {

	private DAOFactory daoFactory;
	private ConsultarParametroClausulaContratoTodosCasoUso casoUso;

	public ConsultarParametroClausulaContratoTodosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarParametroClausulaContratoTodosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<ParametroClausulaContratoEntidad> ejecutar(Void datos) {
		try {
			return casoUso.ejecutar(datos);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}