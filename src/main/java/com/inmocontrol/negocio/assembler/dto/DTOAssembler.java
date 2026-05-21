package com.inmocontrol.negocio.assembler.dto;

public interface DTOAssembler<D, T> {
  D ensamblarDominio(T dto);

  T ensamblarDTO(D dominio);
}
