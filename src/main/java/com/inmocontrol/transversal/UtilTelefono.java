package com.inmocontrol.transversal;

import java.util.regex.Pattern;

public final class UtilTelefono {

  private static final Pattern PATRON_TELEFONO = Pattern.compile("^\\+?[1-9]\\d{6,14}$");

  private UtilTelefono() {
    super();
  }

  public static boolean esTelefonoValido(final String telefono) {
    if (UtilTexto.esNula(telefono)) {
      return false;
    }
    return PATRON_TELEFONO.matcher(telefono.trim()).matches();
  }

  public static String sanitizarTelefono(final String telefono) {
    if (UtilTexto.esNula(telefono)) {
      return UtilTexto.VACIO;
    }
    return telefono.replaceAll("[^\\d+]", "");
  }
}
