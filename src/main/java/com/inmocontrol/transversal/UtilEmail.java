package com.inmocontrol.transversal;

import java.util.regex.Pattern;

public final class UtilEmail {

  private static final Pattern PATRON_EMAIL =
      Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

  private UtilEmail() {
    super();
  }

  public static boolean esEmailValido(final String email) {
    if (UtilTexto.esNula(email)) {
      return false;
    }
    return PATRON_EMAIL.matcher(email.trim()).matches();
  }

  public static String sanitizarEmail(final String email) {
    if (UtilTexto.esNula(email)) {
      return UtilTexto.VACIO;
    }
    return email.trim().toLowerCase();
  }
}
