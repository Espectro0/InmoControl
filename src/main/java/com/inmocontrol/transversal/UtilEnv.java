package com.inmocontrol.transversal;

import com.inmocontrol.transversal.excepcion.InmocontrolExcepcion;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class UtilEnv {

  private static final Properties PROPERTIES = new Properties();
  private static final String ARCHIVO_ENV = ".env";
  private static final String RUTA_PROYECTO = System.getProperty("user.dir");

  static {
    Path rutaEnv = Paths.get(RUTA_PROYECTO, ARCHIVO_ENV);
    try (InputStream input = Files.newInputStream(rutaEnv)) {
      PROPERTIES.load(input);
    } catch (IOException e) {
      throw new InmocontrolExcepcion(
        "No se pudo cargar la configuracion del sistema.",
        "Error al leer archivo .env: " + e.getMessage(),
        e);
    }
  }

  private UtilEnv() {
    super();
  }

  public static String get(String clave) {
    return PROPERTIES.getProperty(clave);
  }

  public static String get(String clave, String valorDefecto) {
    return PROPERTIES.getProperty(clave, valorDefecto);
  }

  public static int getInt(String clave, int valorDefecto) {
    String valor = PROPERTIES.getProperty(clave);
    try {
      return valor != null ? Integer.parseInt(valor) : valorDefecto;
    } catch (NumberFormatException _) {
      return valorDefecto;
    }
  }
}
