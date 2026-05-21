package com.inmocontrol.transversal.excepcion;

public class InmocontrolExcepcion extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String mensajeUsuario;
  private String mensajeTecnico;
  private Exception excepcionRaiz;

  public InmocontrolExcepcion(String mensaje) {
    super(mensaje);
    this.mensajeUsuario = mensaje;
    this.mensajeTecnico = mensaje;
  }

  public InmocontrolExcepcion(String mensaje, Throwable causa) {
    super(mensaje, causa);
    this.mensajeUsuario = mensaje;
    this.mensajeTecnico = mensaje;
    this.excepcionRaiz = (Exception) causa;
  }

  public InmocontrolExcepcion(String mensajeUsuario, String mensajeTecnico, Throwable causa) {
    super(mensajeUsuario, causa);
    this.mensajeUsuario = mensajeUsuario;
    this.mensajeTecnico = mensajeTecnico;
    this.excepcionRaiz = (Exception) causa;
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
