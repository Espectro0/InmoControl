package com.inmocontrol.negocio.assembler.entidad;

public interface EntidadAssembler<E, D> {
  D ensamblarDominio(E entidad);

  E ensamblarEntidad(D dominio);
}
