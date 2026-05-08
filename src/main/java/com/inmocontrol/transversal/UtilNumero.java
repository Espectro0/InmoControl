package com.inmocontrol.transversal;

public final class UtilNumero {

    public static final Integer ENTERO_DEFECTO = null;
    public static final Double DOBLE_DEFECTO = null;

    private UtilNumero() {
        super();
    }

    public static boolean esNulo(final Integer numero) {
        return UtilObjeto.esNulo(numero);
    }

    public static boolean esNulo(final Double numero) {
        return UtilObjeto.esNulo(numero);
    }

    public static Integer obtenerValorDefecto(final Integer numero, final Integer valorDefecto) {
        return UtilObjeto.obtenerValorDefecto(numero, valorDefecto);
    }

    public static Integer obtenerValorDefecto(final Integer numero) {
        return obtenerValorDefecto(numero, ENTERO_DEFECTO);
    }

    public static Double obtenerValorDefecto(final Double numero, final Double valorDefecto) {
        return UtilObjeto.obtenerValorDefecto(numero, valorDefecto);
    }

    public static Double obtenerValorDefecto(final Double numero) {
        return obtenerValorDefecto(numero, DOBLE_DEFECTO);
    }

    public static boolean estaEnRango(
            final Integer numero, final Integer minimo, final Integer maximo) {
        if (esNulo(numero)) {
            return false;
        }
        if (!esNulo(minimo) && numero < minimo) {
            return false;
        }
        if (!esNulo(maximo) && numero > maximo) {
            return false;
        }
        return true;
    }

    public static boolean estaEnRango(
            final Double numero, final Double minimo, final Double maximo) {
        if (esNulo(numero)) {
            return false;
        }
        if (!esNulo(minimo) && numero < minimo) {
            return false;
        }
        if (!esNulo(maximo) && numero > maximo) {
            return false;
        }
        return true;
    }

    public static boolean esPositivo(final Integer numero) {
        return !esNulo(numero) && numero > 0;
    }

    public static boolean esPositivo(final Double numero) {
        return !esNulo(numero) && numero > 0;
    }

    public static boolean esCero(final Integer numero) {
        return !esNulo(numero) && numero == 0;
    }

    public static boolean esCero(final Double numero) {
        return !esNulo(numero) && numero == 0.0;
    }
}
