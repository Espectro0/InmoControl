package com.inmocontrol.transversal;

import java.util.UUID;
import java.util.regex.Pattern;

public final class UtilValidacion {

  private static final String PATRON_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
  private static final Pattern PATRON_EMAIL_COMPILED = Pattern.compile(PATRON_EMAIL);

  private UtilValidacion() {
    super();
  }

  public static boolean esObligatorio(final String valor) {
    return !UtilTexto.esNula(valor) && !valor.trim().isEmpty();
  }

  public static boolean validarLongitud(final String valor, final int min, final int max) {
    if (UtilTexto.esNula(valor)) {
      return false;
    }
    final int longitud = valor.length();
    return longitud >= min && longitud <= max;
  }

  public static boolean validarPatron(final String valor, final String patron) {
    if (UtilTexto.esNula(valor) || UtilTexto.esNula(patron)) {
      return false;
    }
    return Pattern.matches(patron, valor);
  }

  public static boolean validarRangoEntero(final Integer valor, final int min, final int max) {
    if (UtilNumero.esNulo(valor)) {
      return false;
    }
    return valor >= min && valor <= max;
  }

  public static boolean esUUIDValido(final UUID valor) {
    return !UtilObjeto.esNulo(valor);
  }

  public static boolean esEmailValido(final String email) {
    if (UtilTexto.esNula(email)) {
      return false;
    }
    return PATRON_EMAIL_COMPILED.matcher(email.trim()).matches();
  }

  public static boolean esTelefonoValido(final String telefono) {
    if (UtilTexto.esNula(telefono)) {
      return false;
    }
    final String limpio = telefono.replaceAll("[\\s\\-()]", "");
    return limpio.matches("\\d{7,15}");
  }

  public static boolean esIdentificadorValido(final String identificacion) {
    if (UtilTexto.esNula(identificacion)) {
      return false;
    }
    return identificacion.matches("^[a-zA-Z0-9\\-\\.]+$");
  }
}
