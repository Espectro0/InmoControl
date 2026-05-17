package com.inmocontrol.datos.dao.sql;

import java.sql.Connection;

public abstract class SQLDAO {

	private Connection conexion;

	protected SQLDAO(Connection conexion) {
		super();
		this.conexion = conexion;
	}

	protected Connection getConexion() {
		return conexion;
	}

}
