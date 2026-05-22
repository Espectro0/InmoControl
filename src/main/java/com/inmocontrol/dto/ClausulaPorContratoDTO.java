package com.inmocontrol.dto;

import com.inmocontrol.transversal.UtilNumero;
import java.util.UUID;

public final class ClausulaPorContratoDTO {
  private UUID id;
  private Integer numeroClausula;
  private ContratoDTO contrato;
  private ClausulaContratoDTO clausula;

  public ClausulaPorContratoDTO() {}

  private ClausulaPorContratoDTO(final Builder builder) {
    setId(builder.id);
    setNumeroClausula(builder.numeroClausula);
    setContrato(builder.contrato);
    setClausula(builder.clausula);
  }

  public UUID getId() {
    return id;
  }

  public Integer getNumeroClausula() {
    return numeroClausula;
  }

  public ContratoDTO getContrato() {
    return contrato;
  }

  public ClausulaContratoDTO getClausula() {
    return clausula;
  }

  public void setId(final UUID id) {
    this.id = id;
  }

  public void setNumeroClausula(final Integer numeroClausula) {
    this.numeroClausula = UtilNumero.obtenerValorDefecto(numeroClausula);
  }

  public void setContrato(final ContratoDTO contrato) {
    this.contrato = contrato;
  }

  public void setClausula(final ClausulaContratoDTO clausula) {
    this.clausula = clausula;
  }

  public static class Builder {
    private UUID id;
    private Integer numeroClausula;
    private ContratoDTO contrato;
    private ClausulaContratoDTO clausula;

    public Builder id(final UUID id) {
      this.id = id;
      return this;
    }

    public Builder numeroClausula(final Integer numeroClausula) {
      this.numeroClausula = numeroClausula;
      return this;
    }

    public Builder contrato(final ContratoDTO contrato) {
      this.contrato = contrato;
      return this;
    }

    public Builder clausula(final ClausulaContratoDTO clausula) {
      this.clausula = clausula;
      return this;
    }

    public ClausulaPorContratoDTO build() {
      return new ClausulaPorContratoDTO(this);
    }
  }
}
