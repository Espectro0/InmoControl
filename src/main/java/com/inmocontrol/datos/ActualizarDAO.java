package com.inmocontrol.datos;

public interface ActualizarDAO<E, ID> {
  void actualizar(ID id, E entidad);
}
