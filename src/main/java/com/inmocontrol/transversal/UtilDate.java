package com.inmocontrol.transversal;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public final class UtilDate {

  public static final String FORMATO_FECHA = "yyyy-MM-dd";
  public static final Date FECHA_DEFECTO = parsear("1000-01-01", FORMATO_FECHA);

  private UtilDate() {
    super();
  }

  public static boolean esNula(final Date fecha) {
    return UtilObjeto.esNulo(fecha);
  }

  public static Date obtenerValorDefecto(final Date fecha, final Date valorDefecto) {
    return UtilObjeto.obtenerValorDefecto(fecha, valorDefecto);
  }

  public static Date obtenerValorDefecto(final Date fecha) {
    return obtenerValorDefecto(fecha, FECHA_DEFECTO);
  }

  public static String formatear(final Date fecha) {
    return formatear(fecha, FORMATO_FECHA);
  }

  public static String formatear(final Date fecha, final String formato) {
    if (esNula(fecha)) {
      return UtilTexto.VACIO;
    }
    var df = new SimpleDateFormat(UtilTexto.obtenerValorDefecto(formato));
    return df.format(fecha);
  }

  public static Date parsear(final String fechaTexto) {
    return parsear(fechaTexto, FORMATO_FECHA);
  }

  public static Date parsear(final String fechaTexto, final String formato) {
    if (UtilTexto.esNula(fechaTexto)) {
      return FECHA_DEFECTO;
    }
    try {
      var df = new SimpleDateFormat(UtilTexto.obtenerValorDefecto(formato));
      return df.parse(fechaTexto);
    } catch (Exception _) {
      return FECHA_DEFECTO;
    }
  }

  public static boolean esFechaPasada(final Date fecha) {
    if (esNula(fecha)) {
      return false;
    }
    return fecha.before(new Date());
  }

  public static boolean esFechaFutura(final Date fecha) {
    if (esNula(fecha)) {
      return false;
    }
    return fecha.after(new Date());
  }

  public static Integer calcularEdad(Date fechaNacimiento) {
    if (fechaNacimiento == null) {
      return null;
    }
    LocalDate nac;
    if (fechaNacimiento instanceof java.sql.Date sqlDate) {
      nac = sqlDate.toLocalDate();
    } else {
      nac = fechaNacimiento.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }
    return Period.between(nac, java.time.LocalDate.now()).getYears();
  }
}
