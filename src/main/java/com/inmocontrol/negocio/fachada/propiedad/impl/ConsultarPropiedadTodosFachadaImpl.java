package com.inmocontrol.negocio.fachada.propiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.PropiedadEntidad;
import com.inmocontrol.negocio.casouso.propiedad.impl.ConsultarPropiedadTodosCasoUsoImpl;
import com.inmocontrol.negocio.casouso.propiedad.ConsultarPropiedadTodosCasoUso;
import com.inmocontrol.negocio.fachada.propiedad.ConsultarPropiedadTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarPropiedadTodosFachadaImpl implements ConsultarPropiedadTodosFachada {

	private DAOFactory daoFactory;
	private ConsultarPropiedadTodosCasoUso casoUso;

	public ConsultarPropiedadTodosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarPropiedadTodosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<PropiedadEntidad> ejecutar(Void datos) {
		try {
			return casoUso.ejecutar(datos);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}