package com.inmocontrol.transversal;

public final class UtilBoolean {

  public static final Boolean BOOLEAN_DEFECTO = false;

  private UtilBoolean() {
    super();
  }

  public static boolean esNulo(final Boolean valor) {
    return valor == null;
  }

  public static Boolean obtenerValorDefecto(final Boolean valor) {
    return esNulo(valor) ? BOOLEAN_DEFECTO : valor;
  }

  public static Boolean obtenerValorDefecto(final Boolean valor, final Boolean valorDefecto) {
    return esNulo(valor) ? valorDefecto : valor;
  }
}
