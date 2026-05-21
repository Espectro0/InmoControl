package com.inmocontrol.transversal;

import java.util.regex.Pattern;

public final class UtilSanitizacion {

  private static final Pattern PATRON_SQL =
      Pattern.compile(
          "(?i)(\\b(select|union|insert|update|delete|drop|alter|exec|execute|truncate|create)\\b|(--|#|/\\*|\\*/|;)|(\\bor\\b\\s+\\d+=\\d+)|(\\band\\b\\s+\\d+=\\d+)|('|\"|`))");

  private static final Pattern PATRON_HTML = Pattern.compile("<[^>]*>");

  private UtilSanitizacion() {
    super();
  }

  public static String sanitizar(final String valor) {
    if (UtilTexto.esNula(valor)) {
      return UtilTexto.VACIO;
    }
    String resultado = valor.trim();
    resultado = eliminarInyeccionSQL(resultado);
    resultado = eliminarHTML(resultado);
    return resultado;
  }

  public static String eliminarInyeccionSQL(final String valor) {
    if (UtilTexto.esNula(valor)) {
      return UtilTexto.VACIO;
    }
    return PATRON_SQL.matcher(valor).replaceAll("");
  }

  public static String eliminarHTML(final String valor) {
    if (UtilTexto.esNula(valor)) {
      return UtilTexto.VACIO;
    }
    return PATRON_HTML.matcher(valor).replaceAll("");
  }
}
