package com.inmocontrol.transversal;

import java.util.UUID;

public final class UtilUUID {

  public static final UUID UUID_CERO = UUID.fromString("00000000-0000-0000-0000-000000000000");
  private static final int LONGITUD_UUID = 36;

  private UtilUUID() {
    super();
  }

  public static boolean esUUIDValido(final String uuid) {
    if (UtilTexto.esNula(uuid)) {
      return false;
    }
    final String limpio = uuid.trim();
    return limpio.length() == LONGITUD_UUID
        && limpio.matches(
            "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
  }

  public static UUID obtenerUUIDValido(final String uuid) {
    if (!esUUIDValido(uuid)) {
      return UUID_CERO;
    }
    return UUID.fromString(uuid.trim());
  }

  public static boolean esUUIDValido(final UUID uuid) {
    return !UtilObjeto.esNulo(uuid);
  }

  public static boolean esUUIDCero(final UUID uuid) {
    return UUID_CERO.equals(uuid);
  }
}
