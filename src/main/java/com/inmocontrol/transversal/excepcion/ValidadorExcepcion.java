package com.inmocontrol.transversal.excepcion;

public class ValidadorExcepcion extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ValidadorExcepcion(String mensaje) {
    super(mensaje);
  }
}