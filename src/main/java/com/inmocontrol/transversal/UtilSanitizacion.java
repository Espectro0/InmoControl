package com.inmocontrol.transversal;

import java.util.regex.Pattern;

public final class UtilSanitizacion {

    private static final Pattern PATRON_SQL = Pattern.compile(
            "('|;|--|xp_|exec|execute|select|insert|update|delete|drop|create|alter|grant|revoke)"
    );

    private static final Pattern PATRON_HTML = Pattern.compile(
            "<[^>]*>"
    );

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
        return valor.replaceAll("'", "''");
    }

    public static String eliminarHTML(final String valor) {
        if (UtilTexto.esNula(valor)) {
            return UtilTexto.VACIO;
        }
        return PATRON_HTML.matcher(valor).replaceAll("");
    }
}