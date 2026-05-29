package com.inmocontrol.transversal.excepcion;

public class InmocontrolExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String mensajeUsuario;
	private String mensajeTecnico;
	private Exception excepcionRaiz;

	public InmocontrolExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcion) {
		super(mensajeTecnico);
		this.mensajeUsuario = mensajeUsuario;
		this.mensajeTecnico = mensajeTecnico;
		this.excepcionRaiz = excepcion;
	}

	public InmocontrolExcepcion(String mensajeUsuario, String mensajeTecnico) {
		super(mensajeTecnico);
		this.mensajeUsuario = mensajeUsuario;
		this.mensajeTecnico = mensajeTecnico;
		this.excepcionRaiz = null;
	}

	public String getMensajeUsuario() {
		return mensajeUsuario;
	}

	public String getMensajeTecnico() {
		return mensajeTecnico;
	}

	public Exception getExcepcionRaiz() {
		return excepcionRaiz;
	}

	public void setMensajeUsuario(String mensajeUsuario) {
		this.mensajeUsuario = mensajeUsuario;
	}

	public void setMensajeTecnico(String mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	public void setExcepcionRaiz(Exception excepcionRaiz) {
		this.excepcionRaiz = excepcionRaiz;
	}
}
