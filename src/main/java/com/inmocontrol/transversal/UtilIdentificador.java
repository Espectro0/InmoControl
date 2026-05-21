package com.inmocontrol.transversal;

import java.util.regex.Pattern;

public final class UtilIdentificador {

    private static final Pattern PATRON_IDENTIFICADOR = Pattern.compile("^[a-zA-Z0-9\\-\\.]+$");

    private UtilIdentificador() {
        super();
    }

    public static boolean esIdentificadorValido(final String identificacion) {
        if (UtilTexto.esNula(identificacion)) {
            return false;
        }
        return PATRON_IDENTIFICADOR.matcher(identificacion.trim()).matches();
    }

    public static String sanitizarIdentificador(final String identificacion) {
        if (UtilTexto.esNula(identificacion)) {
            return UtilTexto.VACIO;
        }
        return identificacion.trim().toUpperCase();
    }
}