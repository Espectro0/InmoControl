package com.inmocontrol.negocio.fachada.tipopropiedad.impl;

import com.inmocontrol.datos.dao.sql.factoria.DAOFactory;
import com.inmocontrol.entidad.TipoPropiedadEntidad;
import com.inmocontrol.negocio.casouso.tipopropiedad.ConsultarTipoPropiedadTodosCasoUso;
import com.inmocontrol.negocio.casouso.tipopropiedad.impl.ConsultarTipoPropiedadTodosCasoUsoImpl;
import com.inmocontrol.negocio.fachada.tipopropiedad.ConsultarTipoPropiedadTodosFachada;
import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.util.List;

public class ConsultarTipoPropiedadTodosFachadaImpl implements ConsultarTipoPropiedadTodosFachada {

	private DAOFactory daoFactory;
	private ConsultarTipoPropiedadTodosCasoUso casoUso;

	public ConsultarTipoPropiedadTodosFachadaImpl() {
		daoFactory = DAOFactory.getFactory();
		casoUso = new ConsultarTipoPropiedadTodosCasoUsoImpl(daoFactory);
	}

	@Override
	public List<TipoPropiedadEntidad> ejecutar(Void datos) {
		try {
			return casoUso.ejecutar(datos);

		} catch (Exception excepcion) {
			throw new InmocontrolExcepcion("Ocurrio un error obteniendo la informacion", excepcion);

		} finally {
			daoFactory.cerrarConexion();
		}
	}
}