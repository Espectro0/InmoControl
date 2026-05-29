package com.inmocontrol.negocio.assembler.dto.impl;

import com.inmocontrol.dto.ClausulaPorContratoDTO;
import com.inmocontrol.negocio.assembler.dto.DTOAssembler;
import com.inmocontrol.negocio.dominio.ClausulaPorContratoDominio;
import com.inmocontrol.transversal.UtilObjeto;
import com.inmocontrol.transversal.UtilUUID;
import com.inmocontrol.transversal.excepcion.ValidadorExcepcion;

public final class ClausulaPorContratoDTOAssembler
    implements DTOAssembler<ClausulaPorContratoDominio, ClausulaPorContratoDTO> {
  private static DTOAssembler<ClausulaPorContratoDominio, ClausulaPorContratoDTO> INSTANCE;

  private ClausulaPorContratoDTOAssembler() {
    super();
  }

  public static final synchronized DTOAssembler<ClausulaPorContratoDominio, ClausulaPorContratoDTO>
      getInstance() {
    if (UtilObjeto.esNulo(INSTANCE)) {
      INSTANCE = new ClausulaPorContratoDTOAssembler();
    }
    return INSTANCE;
  }

  @Override
  public ClausulaPorContratoDominio ensamblarDominio(final ClausulaPorContratoDTO dto) {
    var entidadAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dto, new ClausulaPorContratoDTO.Builder().build());
    var clausulaPorContrato = new ClausulaPorContratoDominio.Builder()
        .id(entidadAEnsamblar.getId())
        .numeroClausula(entidadAEnsamblar.getNumeroClausula())
        .contrato(
            ContratoDTOAssembler.getInstance().ensamblarDominio(entidadAEnsamblar.getContrato()))
        .clausula(
            ClausulaContratoDTOAssembler.getInstance()
                .ensamblarDominio(entidadAEnsamblar.getClausula()))
        .build();
    if (clausulaPorContrato.getContrato().getId().equals(UtilUUID.UUID_CERO)) {
      throw new ValidadorExcepcion("El contrato es obligatorio");
    }
    if (clausulaPorContrato.getClausula().getId().equals(UtilUUID.UUID_CERO)) {
      throw new ValidadorExcepcion("La cláusula es obligatoria");
    }
    return clausulaPorContrato;
  }

  @Override
  public ClausulaPorContratoDTO ensamblarDTO(final ClausulaPorContratoDominio dominio) {
    var dominioAEnsamblar =
        UtilObjeto.obtenerValorDefecto(dominio, new ClausulaPorContratoDominio.Builder().build());
    return new ClausulaPorContratoDTO.Builder()
        .id(dominioAEnsamblar.getId())
        .numeroClausula(dominioAEnsamblar.getNumeroClausula())
        .contrato(ContratoDTOAssembler.getInstance().ensamblarDTO(dominioAEnsamblar.getContrato()))
        .clausula(
            ClausulaContratoDTOAssembler.getInstance()
                .ensamblarDTO(dominioAEnsamblar.getClausula()))
        .build();
  }
}
